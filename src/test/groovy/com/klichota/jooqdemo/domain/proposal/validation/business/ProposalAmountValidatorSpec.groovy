package com.klichota.jooqdemo.domain.proposal.validation.business

import com.klichota.jooqdemo.ParamEngineSpecification
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.proposal.validation.ValidationParam

class ProposalAmountValidatorSpec extends ParamEngineSpecification {

    ProposalAmountValidator validator

    def setup() {
        validator = new ProposalAmountValidator(engine)

        mockParam(ValidationParam.PROPOSAL_MIN_AMOUNT.name(), Consts.MIN_AMOUNT)
        mockParam(ValidationParam.PROPOSAL_MAX_AMOUNT.name(), Consts.MAX_AMOUNT)
    }

    def "should not return errors when amount is between min and max"() {
        given:
        def proposal = new Proposal([amount: Consts.MAX_AMOUNT])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.isEmpty()
    }

    def "should return error when amount is less then min amount"() {
        given:
        def proposal = new Proposal([amount: Consts.MIN_AMOUNT - 1])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes[0] == ProposalAmountValidator.VALIDATOR_NAME + '_' + ProposalAmountValidator.Codes.AMOUNT_IS_LESS_THEN_MIN
    }

    def "should return error when amount is greater then max amount"() {
        given:
        def proposal = new Proposal([amount: Consts.MAX_AMOUNT + 1])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes[0] == ProposalAmountValidator.VALIDATOR_NAME + '_' + ProposalAmountValidator.Codes.AMOUNT_IS_GREATER_THEN_MAX
    }

    interface Consts {
        def MIN_AMOUNT = 1_000.0
        def MAX_AMOUNT = 20_000.0
    }
}
