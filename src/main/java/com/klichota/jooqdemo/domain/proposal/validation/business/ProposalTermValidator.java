package com.klichota.jooqdemo.domain.proposal.validation.business;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.param.ParamEngine;
import com.klichota.jooqdemo.domain.proposal.validation.SingleProposalValidator;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationParam;
import com.klichota.jooqdemo.domain.proposal.validation.SingleValidationResult;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@Qualifier(value = ValidationType.BUSINESS)
class ProposalTermValidator implements SingleProposalValidator {

    static final String VALIDATOR_NAME = "TERM_VALIDATOR";

    interface Codes {
        String TERM_IS_LESS_THEN_MIN = "TERM_IS_LESS_THEN_MIN";
        String TERM_IS_GREATER_THEN_MAX = "TERM_IS_GREATER_THEN_MAX";
    }

    private final ParamEngine engine;

    ProposalTermValidator(ParamEngine engine) {
        this.engine = engine;
    }

    @Override
    public SingleValidationResult validate(Proposal proposal) {
        SingleValidationResult result = new SingleValidationResult(VALIDATOR_NAME);

        validateMinTerm(proposal, result);
        validateMaxTerm(proposal, result);

        return result;
    }

    private void validateMinTerm(Proposal proposal, SingleValidationResult result) {
        if (proposal.getTerm() < engine.getInt(ValidationParam.PROPOSAL_MIN_TERM.name()).getValue()) {
            result.addError(Codes.TERM_IS_LESS_THEN_MIN);
        }
    }

    private void validateMaxTerm(Proposal proposal, SingleValidationResult result) {
        if (proposal.getTerm() > engine.getInt(ValidationParam.PROPOSAL_MAX_TERM.name()).getValue()) {
            result.addError(Codes.TERM_IS_GREATER_THEN_MAX);
        }
    }
}
