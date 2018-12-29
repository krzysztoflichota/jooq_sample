package com.klichota.jooqdemo.domain.proposal.validation.business

import com.klichota.jooqdemo.ParamEngineSpecification
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.proposal.validation.ValidationParam

class ProposalTermValidatorSpec extends ParamEngineSpecification {

    ProposalTermValidator validator

    def setup() {
        validator = new ProposalTermValidator(engine)

        mockParam(ValidationParam.PROPOSAL_MIN_TERM.name(), Consts.MIN_TERM)
        mockParam(ValidationParam.PROPOSAL_MAX_TERM.name(), Consts.MAX_TERM)
    }

    def "should not return errors when term is between min and max"() {
        given:
        def proposal = new Proposal([term: Consts.MAX_TERM])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.isEmpty()
    }

    def "should return error when term is less then min term"() {
        given:
        def proposal = new Proposal([term: Consts.MIN_TERM - 1])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes[0] == ProposalTermValidator.VALIDATOR_NAME + '_' + ProposalTermValidator.Codes.TERM_IS_LESS_THEN_MIN
    }

    def "should return error when term is greater then max term"() {
        given:
        def proposal = new Proposal([term: Consts.MAX_TERM + 1])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes[0] == ProposalTermValidator.VALIDATOR_NAME + '_' + ProposalTermValidator.Codes.TERM_IS_GREATER_THEN_MAX
    }

    interface Consts {
        def MIN_TERM = 30
        def MAX_TERM = 90
    }
}
