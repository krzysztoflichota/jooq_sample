package com.klichota.jooqdemo.domain.proposal.validation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;


@Data
public class ValidationSummary {

    private final List<String> errorCodes;
    private final boolean passedIntegrityValidations;

    public ValidationSummary(List<SingleValidationResult> results, boolean passedIntegrityValidations) {
        this.errorCodes = results.stream()
                .map(SingleValidationResult::getErrorCodes)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        this.passedIntegrityValidations = passedIntegrityValidations;
    }

    public boolean isValid() {
        return errorCodes.isEmpty();
    }
}
