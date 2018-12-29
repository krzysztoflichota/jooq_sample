package com.klichota.jooqdemo.domain.proposal.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;


@Data
public class SingleValidationResult {

    public static final String ERROR_CODE_DELIMITER = "_";

    private final String validatorName;
    private final List<String> errorCodes = new ArrayList<>();

    public void addError(String errorCode) {
        errorCodes.add(errorCode);
    }

    public boolean isValid() {
        return errorCodes.isEmpty();
    }

    public List<String> getErrorCodes() {
        return errorCodes.stream()
                .map(code -> validatorName + ERROR_CODE_DELIMITER + code)
                .collect(Collectors.toList());
    }
}
