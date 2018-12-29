package com.klichota.jooqdemo.domain.proposal.validation


import spock.lang.Specification

class ValidationSummarySpec extends Specification {

    def "should return if proposal is valid"() {
        expect:
        new ValidationSummary(Collections.emptyList(), true).isValid()
    }

    def "should merge all single validation results"() {
        given:
        def validators = [Mock(SingleValidationResult.class), Mock(SingleValidationResult.class)]
        validators[0].errorCodes >> ['error1']
        validators[1].errorCodes >> ['error2']

        when:
        def result = new ValidationSummary(validators, false)

        then:
        !result.isValid()
        result.errorCodes == [Consts.ERROR_1, Consts.ERROR_2]
    }

    interface Consts {
        def ERROR_1 = 'error1'
        def ERROR_2 = 'error2'
    }
}
