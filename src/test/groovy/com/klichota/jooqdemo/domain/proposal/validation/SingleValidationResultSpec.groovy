package com.klichota.jooqdemo.domain.proposal.validation

import spock.lang.Specification

class SingleValidationResultSpec extends Specification {

    def "should collect errors codes"() {
        given:
        def validationResult = new SingleValidationResult(Consts.VALIDATOR_NAME)

        when:
        validationResult.addError('field is null')

        then:
        validationResult.errorCodes.size() == 1
    }

    def "should tell if is valid"() {
        given:
        def validationResult = new SingleValidationResult(Consts.VALIDATOR_NAME)

        when:
        def result = validationResult.isValid()

        then:
        result
    }

    def "should tell iif is invalid"() {
        given:
        def validationResult = new SingleValidationResult(Consts.VALIDATOR_NAME)
        validationResult.addError('some error')

        when:
        def result = validationResult.isValid()

        then:
        !result
    }

    def "should properly concat error codes with validator name"() {
        given:
        def errorCode1 = 'ERROR_1'
        def errorCode2 = 'ERROR_2'
        def validationResult = new SingleValidationResult(Consts.VALIDATOR_NAME)
        validationResult.addError('ERROR_1')
        validationResult.addError('ERROR_2')

        when:
        def result = validationResult.getErrorCodes()

        then:
        result == [Consts.VALIDATOR_NAME + '_' + errorCode1, Consts.VALIDATOR_NAME + '_' + errorCode2]
    }

    interface Consts {
        def VALIDATOR_NAME = 'vName'
    }
}
