package com.klichota.jooqdemo.domain.loan;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.LoanExtension;
import com.klichota.jooqdemo.domain.datetime.DateTimeService;
import com.klichota.jooqdemo.domain.datetime.DateUtils;
import com.klichota.jooqdemo.domain.param.ParamEngine;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class ApplyForLoanExtensionService {

    private final LoanService loanService;
    private final LoanExtensionService loanExtensionService;
    private final ParamEngine engine;
    private final DateTimeService dateTimeService;

    ApplyForLoanExtensionService(LoanService loanService, LoanExtensionService loanExtensionService, ParamEngine engine, DateTimeService dateTimeService) {
        this.loanService = loanService;
        this.loanExtensionService = loanExtensionService;
        this.engine = engine;
        this.dateTimeService = dateTimeService;
    }

    public LoanExtension apply(long loanId) {
        log.info("Extending loan with id {}", loanId);
        return loanService.fetch(loanId)
                .map(this::createExtension)
                .orElseThrow(() -> new IllegalArgumentException("No such loan to extend"));
    }

    private LoanExtension createExtension(Loan loan) {
        List<LoanExtension> extensions = loanExtensionService.fetchExtensions(loan.getId());
        LoanExtension extension = extensions.isEmpty() ?
                createExtensionFromLoan(loan, loan.getDateTo()) :
                createExtensionFromLoan(loan, getRecentExtension(extensions).getExtendedTo());
        LoanExtension savedExtension = loanExtensionService.save(extension);
        loan.setStatus(LoanStatus.EXTENDED.name());
        loanService.update(loan);
        return savedExtension;
    }

    private LoanExtension createExtensionFromLoan(Loan loan, Date dateToExtend) {
        LoanExtension extension = new LoanExtension();
        extension.setLoanId(loan.getId());
        long daysToExtend = engine.getInt(LoanParam.DAYS_TO_EXTEND.name()).getValue();
        LocalDate extendedDateTo = DateUtils.asLocalDate(dateToExtend).plusDays(daysToExtend);
        log.info("Loan extended to {}", extendedDateTo);
        extension.setExtendedTo(new Date(DateUtils.asDate(extendedDateTo).getTime()));
        extension.setCreatedAt(dateTimeService.getCurrentTimestamp());
        return extension;
    }

    private LoanExtension getRecentExtension(List<LoanExtension> extensions) {
        return extensions.stream()
                .max(Comparator.comparing(LoanExtension::getExtendedTo))
                .orElseThrow();
    }
}
