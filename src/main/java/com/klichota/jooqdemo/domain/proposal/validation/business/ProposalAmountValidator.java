package com.klichota.jooqdemo.domain.proposal.validation.business;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.param.ParamEngine;
import com.klichota.jooqdemo.domain.proposal.validation.SingleProposalValidator;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationParam;
import com.klichota.jooqdemo.domain.proposal.validation.SingleValidationResult;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationType;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(2)
@Qualifier(value = ValidationType.BUSINESS)
class ProposalAmountValidator implements SingleProposalValidator {

    static final String VALIDATOR_NAME = "AMOUNT_VALIDATOR";

    interface Codes {
        String AMOUNT_IS_LESS_THEN_MIN = "AMOUNT_IS_LESS_THEN_MIN";
        String AMOUNT_IS_GREATER_THEN_MAX = "AMOUNT_IS_GREATER_THEN_MAX";
    }

    private final ParamEngine engine;

    ProposalAmountValidator(ParamEngine engine) {
        this.engine = engine;
    }

    @Override
    public SingleValidationResult validate(Proposal proposal) {
        SingleValidationResult result = new SingleValidationResult(VALIDATOR_NAME);

        validateMinAmount(proposal, result);
        validateMaxAmount(proposal, result);

        return result;
    }

    private void validateMinAmount(Proposal proposal, SingleValidationResult result) {
        BigDecimal minAmount = engine.getDec(ValidationParam.PROPOSAL_MIN_AMOUNT.name()).getValue();
        if (minAmount.compareTo(proposal.getAmount()) > 0) {
            result.addError(Codes.AMOUNT_IS_LESS_THEN_MIN);
        }
    }

    private void validateMaxAmount(Proposal proposal, SingleValidationResult result) {
        BigDecimal maxAmount = engine.getDec(ValidationParam.PROPOSAL_MAX_AMOUNT.name()).getValue();
        if (maxAmount.compareTo(proposal.getAmount()) < 0) {
            result.addError(Codes.AMOUNT_IS_GREATER_THEN_MAX);
        }
    }
}
