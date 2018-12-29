package com.klichota.jooqdemo.domain.proposal.validation.integrity;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.proposal.validation.SingleProposalValidator;
import com.klichota.jooqdemo.domain.proposal.validation.SingleValidationResult;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@Qualifier(value = ValidationType.INTEGRITY)
class EmptyFieldsValidator implements SingleProposalValidator {

    static final String VALIDATOR_NAME = "EMPTY_FIELDS_VALIDATOR";

    interface Codes {
        String TERM_IS_EMPTY = "TERM_IS_EMPTY";
        String AMOUNT_IS_EMPTY = "AMOUNT_IS_EMPTY";
    }

    @Override
    public SingleValidationResult validate(Proposal proposal) {
        SingleValidationResult result = new SingleValidationResult(VALIDATOR_NAME);

        validateTermIfEmpty(proposal, result);
        validateAmountIfEmpty(proposal, result);

        return result;
    }

    private void validateTermIfEmpty(Proposal proposal, SingleValidationResult result) {
        if (proposal.getTerm() == null) {
            result.addError(EmptyFieldsValidator.Codes.TERM_IS_EMPTY);
        }
    }

    private void validateAmountIfEmpty(Proposal proposal, SingleValidationResult result) {
        if (proposal.getAmount() == null) {
            result.addError(EmptyFieldsValidator.Codes.AMOUNT_IS_EMPTY);
        }
    }
}
