package com.klichota.jooqdemo.domain.proposal.validation.business

import com.klichota.jooqdemo.ParamEngineSpecification
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.datetime.DateTimeService
import com.klichota.jooqdemo.domain.proposal.validation.ValidationParam

import java.time.LocalDateTime

class MaxAmountApplyTimeValidatorSpec extends ParamEngineSpecification {

    DateTimeService dateTimeService
    MaxAmountApplyTimeValidator validator

    def setup() {
        dateTimeService = Mock()
        validator = new MaxAmountApplyTimeValidator(engine, dateTimeService)

        mockParam(ValidationParam.PROPOSAL_MAX_AMOUNT_INVALID_MIN_APPLY_TIME.name(), Consts.MIN_APPLY_HOUR)
        mockParam(ValidationParam.PROPOSAL_MAX_AMOUNT_INVALID_MAX_APPLY_TIME.name(), Consts.MAX_APPLY_HOUR)
        mockParam(ValidationParam.PROPOSAL_MAX_AMOUNT.name(), Consts.MAX_AMOUNT)
    }

    def "should not return error when apply outside invalid min and max apply time"() {
        given:
        dateTimeService.getCurrentDateTime() >> LocalDateTime.parse("2018-12-28T18:30:00")
        def proposal = new Proposal([amount: Consts.MAX_AMOUNT])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.isEmpty()
    }

    def "should return error when apply time is between min and max and max amount is asked"() {
        given:
        dateTimeService.getCurrentDateTime() >> LocalDateTime.parse("2018-12-28T03:30:00")
        def proposal = new Proposal([amount: Consts.MAX_AMOUNT])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.contains(MaxAmountApplyTimeValidator.VALIDATOR_NAME + '_'
                + MaxAmountApplyTimeValidator.Codes.MAX_AMOUNT_WITH_INVALID_APPLY_TIME)
    }

    interface Consts {
        def MIN_APPLY_HOUR = 0
        def MAX_APPLY_HOUR = 6
        def MAX_AMOUNT = 20_000.0
    }
}
