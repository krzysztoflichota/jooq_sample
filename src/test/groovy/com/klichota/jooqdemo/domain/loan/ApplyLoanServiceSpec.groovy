package com.klichota.jooqdemo.domain.loan

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.param.ParamEngine
import com.klichota.jooqdemo.domain.proposal.ProposalService
import com.klichota.jooqdemo.domain.proposal.ProposalStatus
import com.klichota.jooqdemo.domain.proposal.validation.SingleValidationResult
import com.klichota.jooqdemo.domain.proposal.validation.ValidationService
import com.klichota.jooqdemo.domain.proposal.validation.ValidationSummary
import spock.lang.Specification

class ApplyLoanServiceSpec extends Specification {

    ValidationService validationService
    ProposalService proposalService
    LoanService loanService
    LoanFactory factory
    ApplyLoanService service

    def setup() {
        validationService = Mock()
        proposalService = Mock()
        loanService = Mock()
        factory = Mock()
        service = new ApplyLoanService(validationService, proposalService, loanService, factory)
    }

    def "should proceed loan if all validations are passed"() {
        given:
        def proposal = new Proposal([term: 40, amount: 20000.0])
        def savedProposal = new Proposal(proposal)
        savedProposal.setId(Consts.PROPOSAL_ID)
        validationService.validate(proposal) >> new ValidationSummary(Collections.emptyList(), true)
        proposalService.save(proposal, ProposalStatus.ACCEPTED) >> savedProposal

        when:
        def result = service.apply(proposal)

        then:
        result.loanId == Consts.LOAN_ID
        1 * proposalService.save(_, ProposalStatus.ACCEPTED) >> savedProposal
        1 * loanService.save(_) >> new Loan([id: Consts.LOAN_ID])
    }

    def "should not save any if integrity validations failed"() {
        given:
        def proposal = new Proposal()
        validationService.validate(proposal) >> new ValidationSummary(Collections.emptyList(), false)

        when:
        service.apply(proposal)

        then:
        0 * proposalService.save(_, _)
        0 * loanService.save(_)
    }

    def "should save proposal when integrity validation passed but business failed"() {
        given:
        def proposal = new Proposal()
        def validationResult = new SingleValidationResult('vName')
        validationResult.addError('error1')
        validationService.validate(proposal) >>
                new ValidationSummary([validationResult], true)

        when:
        service.apply(proposal)

        then:
        1 * proposalService.save(_, ProposalStatus.REJECTED)
        0 * loanService.save(_)
    }

    interface Consts {
        def PROPOSAL_ID = 10L
        def LOAN_ID = 5L
    }
}
