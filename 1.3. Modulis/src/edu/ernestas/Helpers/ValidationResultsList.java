package edu.ernestas.Helpers;

import java.util.ArrayList;
import java.util.List;

public class ValidationResultsList {
    private List<ValidationResult> validationResults;

    public ValidationResultsList() {
        validationResults = new ArrayList<>();
    }

    public void addValidationResult(ValidationResult validationResult) {
        if (!validationResult.getStatus()) {
            validationResults.add(validationResult);
        }
    }

    public ValidationResult getValidationResult() {
        for (ValidationResult validationResult : validationResults) {
            if (!validationResult.getStatus()) {
                return validationResult;
            }
        }
        return new ValidationResult();
    }
}
