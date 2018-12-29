package com.klichota.jooqdemo.domain.proposal;

import com.klichota.jooqdemo.boundary.perstitence.Sequences;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.ProposalDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.datetime.DateTimeService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
public class ProposalService {

    private final ProposalDao dao;
    private final DateTimeService dateTimeService;
    private final DSLContext context;

    ProposalService(ProposalDao dao, DateTimeService dateTimeService, DSLContext context) {
        this.dao = dao;
        this.dateTimeService = dateTimeService;
        this.context = context;
    }

    public Proposal save(Proposal proposal, ProposalStatus status) {
        log.info("Saving proposal with status {} for {} and {} days",
                status, proposal.getAmount(), proposal.getTerm());
        Proposal toSave = new Proposal(proposal);
        Long nextId = context.nextval(Sequences.PROPOSAL_SEQ);
        toSave.setId(nextId);
        toSave.setCreatedAt(dateTimeService.getCurrentTimestamp());
        toSave.setStatus(status.name());
        dao.insert(toSave);
        return toSave;
    }
}
