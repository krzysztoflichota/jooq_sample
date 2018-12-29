package com.klichota.jooqdemo.domain.proposal.validation.integrity

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import spock.lang.Specification

class EmptyFieldsValidatorSpec extends Specification {

    EmptyFieldsValidator validator

    def setup() {
        validator = new EmptyFieldsValidator()
    }

    def "should not return errors when proposal hasn't empty fields"() {
        given:
        def proposal = new Proposal([term: 40, amount: 2000])

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.isEmpty()
    }

    def "should return error when term is empty"() {
        given:
        def proposal = new Proposal()

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.contains(EmptyFieldsValidator.VALIDATOR_NAME + '_' + EmptyFieldsValidator.Codes.TERM_IS_EMPTY)
    }

    def "should return error when amount is empty"() {
        given:
        def proposal = new Proposal()

        when:
        def result = validator.validate(proposal)

        then:
        result.errorCodes.contains(EmptyFieldsValidator.VALIDATOR_NAME + '_' + EmptyFieldsValidator.Codes.AMOUNT_IS_EMPTY)
    }
}
