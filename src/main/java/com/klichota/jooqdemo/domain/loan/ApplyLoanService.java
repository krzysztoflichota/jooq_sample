package com.klichota.jooqdemo.domain.loan;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.proposal.ProposalService;
import com.klichota.jooqdemo.domain.proposal.ProposalStatus;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationService;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
public class ApplyLoanService {

    private final ValidationService validationService;
    private final ProposalService proposalService;
    private final LoanService loanService;
    private final LoanFactory factory;

    ApplyLoanService(ValidationService validationService, ProposalService proposalService, LoanService loanService, LoanFactory factory) {
        this.validationService = validationService;
        this.proposalService = proposalService;
        this.loanService = loanService;
        this.factory = factory;
    }

    public ApplyLoanResult apply(Proposal proposal) {
        log.info("Applying for new loan");
        ValidationSummary summary = validationService.validate(proposal);
        if (!summary.isPassedIntegrityValidations()) {
            log.info("Proposal rejected because integrity validations failed");
            return new ApplyLoanResult(summary, proposal, null);
        }
        Proposal savedProposal = saveProposal(proposal, summary);
        if (!summary.isValid()) {
            log.info("Proposal rejected because business validations failed");
            return new ApplyLoanResult(summary, savedProposal, null);
        }
        Loan savedLoan = saveLoan(savedProposal);
        log.info("Proposal passed, new loan created with id {}", savedLoan.getId());
        return new ApplyLoanResult(summary, savedProposal, savedLoan.getId());
    }

    private Proposal saveProposal(Proposal proposal, ValidationSummary summary) {
        ProposalStatus proposalStatus = summary.isValid() ? ProposalStatus.ACCEPTED : ProposalStatus.REJECTED;
        return proposalService.save(proposal, proposalStatus);
    }

    private Loan saveLoan(Proposal savedProposal) {
        Loan loan = factory.create(savedProposal);
        return loanService.save(loan);
    }
}
