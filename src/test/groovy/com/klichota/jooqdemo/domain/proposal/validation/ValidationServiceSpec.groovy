package com.klichota.jooqdemo.domain.proposal.validation

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import spock.lang.Specification

class ValidationServiceSpec extends Specification {

    SingleProposalValidator integrityValidator
    SingleProposalValidator businessValidator
    ValidationService service

    def setup() {
        integrityValidator = Mock()
        businessValidator = Mock()
        service = new ValidationService([integrityValidator], [businessValidator])
    }

    def "should break validations if integrity validations returned error"() {
        given:
        def proposal = new Proposal()
        integrityValidator.validate(proposal) >> createValidationResult(Consts.VALIDATOR_NAME, Consts.INTEGRITY_ERROR_CODE)

        when:
        def validationSummary = service.validate(proposal)

        then:
        validationSummary.errorCodes == [Consts.VALIDATOR_NAME + '_' + Consts.INTEGRITY_ERROR_CODE]
        0 * businessValidator.validate(_) >> createValidationResult(Consts.VALIDATOR_NAME, Consts.BUSINESS_ERROR_CODE)
    }

    def "should proceed with business validations if integrity validations passed"() {
        given:
        def proposal = new Proposal()

        when:
        def validationSummary = service.validate(proposal)

        then:
        validationSummary.errorCodes == [Consts.VALIDATOR_NAME + '_' + Consts.BUSINESS_ERROR_CODE]
        validationSummary.passedIntegrityValidations
        1 * integrityValidator.validate(proposal) >>
                createValidationResult(Consts.VALIDATOR_NAME, null)
        1 * businessValidator.validate(proposal) >>
                createValidationResult(Consts.VALIDATOR_NAME, Consts.BUSINESS_ERROR_CODE)
    }

    def createValidationResult(String name, String errorCode) {
        def result = new SingleValidationResult(name)
        if (errorCode) {
            result.addError(errorCode)
        }
        return result
    }

    interface Consts {
        def VALIDATOR_NAME = 'vName'
        def INTEGRITY_ERROR_CODE = 'error1'
        def BUSINESS_ERROR_CODE = 'error2'
    }
}
