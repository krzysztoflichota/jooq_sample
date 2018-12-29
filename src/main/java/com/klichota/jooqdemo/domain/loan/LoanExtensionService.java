package com.klichota.jooqdemo.domain.loan;

import com.klichota.jooqdemo.boundary.perstitence.Sequences;
import com.klichota.jooqdemo.boundary.perstitence.Tables;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.LoanExtensionDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.LoanExtension;
import com.klichota.jooqdemo.domain.datetime.DateTimeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
class LoanExtensionService {

    private final LoanExtensionDao dao;
    private final DateTimeService dateTimeService;
    private final DSLContext context;

    LoanExtensionService(LoanExtensionDao dao, DateTimeService dateTimeService, DSLContext context) {
        this.dao = dao;
        this.dateTimeService = dateTimeService;
        this.context = context;
    }

    LoanExtension save(LoanExtension extension) {
        LoanExtension toSave = new LoanExtension(extension);
        Long nextId = context.nextval(Sequences.LOAN_EXTENSION_SEQ);
        toSave.setId(nextId);
        toSave.setCreatedAt(dateTimeService.getCurrentTimestamp());
        dao.insert(toSave);
        return toSave;
    }

    List<LoanExtension> fetchExtensions(long loanId) {
        return dao.fetchByLoanId(loanId);
    }
}
