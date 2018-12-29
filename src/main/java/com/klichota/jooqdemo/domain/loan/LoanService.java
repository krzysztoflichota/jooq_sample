package com.klichota.jooqdemo.domain.loan;

import com.klichota.jooqdemo.boundary.perstitence.Sequences;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.LoanDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan;
import com.klichota.jooqdemo.domain.datetime.DateTimeService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
class LoanService {

    private final LoanDao dao;
    private final DateTimeService dateTimeService;
    private final DSLContext context;

    LoanService(LoanDao dao, DateTimeService dateTimeService, DSLContext context) {
        this.dao = dao;
        this.dateTimeService = dateTimeService;
        this.context = context;
    }

    Loan save(Loan loan) {
        log.info("Saving new loan for {} created from {} proposal",
                loan.getAmount(), loan.getProposalId());
        Loan toSave = new Loan(loan);
        Long nextId = context.nextval(Sequences.LOAN_SEQ);
        toSave.setId(nextId);
        toSave.setCreatedAt(dateTimeService.getCurrentTimestamp());
        dao.insert(toSave);
        return toSave;
    }

    Loan update(Loan loan) {
        Loan toUpdate = new Loan(loan);
        dao.update(toUpdate);
        return toUpdate;
    }

    Optional<Loan> fetch(long loanId) {
        return Optional.ofNullable(dao.fetchOneById(loanId));
    }
}
