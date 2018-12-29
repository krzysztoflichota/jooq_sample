package com.klichota.jooqdemo.domain.proposal.validation.business;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.datetime.DateTimeService;
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
@Order(3)
@Qualifier(value = ValidationType.BUSINESS)
class MaxAmountApplyTimeValidator implements SingleProposalValidator {

    static final String VALIDATOR_NAME = "MAX_AMOUNT_APPLY_TIME_VALIDATOR";

    interface Codes {
        String MAX_AMOUNT_WITH_INVALID_APPLY_TIME = "MAX_AMOUNT_WITH_INVALID_APPLY_TIME";
    }

    private final ParamEngine engine;
    private final DateTimeService dateTimeService;

    MaxAmountApplyTimeValidator(ParamEngine engine, DateTimeService dateTimeService) {
        this.engine = engine;
        this.dateTimeService = dateTimeService;
    }

    @Override
    public SingleValidationResult validate(Proposal proposal) {
        SingleValidationResult result = new SingleValidationResult(VALIDATOR_NAME);
        BigDecimal maxAmount = engine.getDec(ValidationParam.PROPOSAL_MAX_AMOUNT.name()).getValue();
        long minApplyHour = engine.getInt(ValidationParam.PROPOSAL_MAX_AMOUNT_INVALID_MIN_APPLY_TIME.name()).getValue();
        long maxApplyHour = engine.getInt(ValidationParam.PROPOSAL_MAX_AMOUNT_INVALID_MAX_APPLY_TIME.name()).getValue();
        int applyHour = dateTimeService.getCurrentDateTime().getHour();

        if (maxAmountIsAsked(proposal, maxAmount) && applyTimeBetweenInvalidMinMax(minApplyHour, maxApplyHour, applyHour)) {
            result.addError(Codes.MAX_AMOUNT_WITH_INVALID_APPLY_TIME);
        }

        return result;
    }

    private boolean applyTimeBetweenInvalidMinMax(long minApplyHour, long maxApplyHour, int applyHour) {
        return applyHour >= minApplyHour && applyHour <= maxApplyHour;
    }

    private boolean maxAmountIsAsked(Proposal proposal, BigDecimal maxAmount) {
        return maxAmount.compareTo(proposal.getAmount()) == 0;
    }
}
