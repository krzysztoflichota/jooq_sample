package com.klichota.jooqdemo.domain.proposal.validation;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ValidationService {

    private final List<SingleProposalValidator> integrityValidators;
    private final List<SingleProposalValidator> businessValidators;

    ValidationService(
            @Qualifier(ValidationType.INTEGRITY) List<SingleProposalValidator> integrityValidators,
            @Qualifier(ValidationType.BUSINESS) List<SingleProposalValidator> businessValidators
    ) {
        this.integrityValidators = integrityValidators;
        this.businessValidators = businessValidators;
        log.info("integrity validators: {}, business validators: {}", integrityValidators, businessValidators);
    }

    public ValidationSummary validate(Proposal proposal) {
        log.info("Validating loan proposal from {} for {} and {} days",
                proposal.getCreatedAt(), proposal.getAmount(), proposal.getTerm());

        List<SingleValidationResult> validationResults = executeValidations(integrityValidators, proposal);
        boolean integrityValidationsPassed = allValidationsPassed(validationResults);
        if (integrityValidationsPassed) {
            log.debug("All integrity validations passed, passing to business validations");
            validationResults.addAll(executeValidations(businessValidators, proposal));
        } else {
            log.debug("Integrity validations failed, can't pass to business validations");
        }
        return new ValidationSummary(validationResults, integrityValidationsPassed);
    }

    private List<SingleValidationResult> executeValidations(List<SingleProposalValidator> validators, Proposal proposal) {
        return validators.stream()
                .map(validator -> validator.validate(proposal))
                .collect(Collectors.toList());
    }

    private boolean allValidationsPassed(List<SingleValidationResult> validationResults) {
        return validationResults.stream().allMatch(SingleValidationResult::isValid);
    }
}
