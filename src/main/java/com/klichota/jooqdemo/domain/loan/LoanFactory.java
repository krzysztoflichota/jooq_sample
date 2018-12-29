package com.klichota.jooqdemo.domain.loan;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.datetime.DateTimeService;
import com.klichota.jooqdemo.domain.datetime.DateUtils;
import com.klichota.jooqdemo.domain.param.ParamEngine;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.stereotype.Service;


@Service
class LoanFactory {

    private static final long INTERVAL_TO_START = 1;

    private final DateTimeService dateTimeService;
    private final ParamEngine engine;

    LoanFactory(DateTimeService dateTimeService, ParamEngine engine) {
        this.dateTimeService = dateTimeService;
        this.engine = engine;
    }

    public Loan create(Proposal proposal) {
        Loan loan = new Loan();
        loan.setAmount(proposal.getAmount());
        loan.setStatus(LoanStatus.ACTIVE.name());
        loan.setProposalId(proposal.getId());

        LocalDate currentDate = dateTimeService.getCurrentDate();
        LocalDate dateFrom = currentDate.plusDays(INTERVAL_TO_START);
        LocalDate dateTo = currentDate.plusDays(INTERVAL_TO_START + proposal.getTerm());

        loan.setDateFrom(new Date(DateUtils.asDate(dateFrom).getTime()));
        loan.setDateTo(new Date(DateUtils.asDate(dateTo).getTime()));

        BigDecimal costMultiplier = engine.getDec(LoanParam.PRINCIPAL_MULTIPLIER.name()).getValue();
        loan.setCost(proposal.getAmount().multiply(costMultiplier));
        return loan;
    }
}
