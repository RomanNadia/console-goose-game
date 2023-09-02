package com.goose.validation;

public class ValidationInfo {
    private boolean validationStatus;
    private String falseValidationMessage = "";

    public ValidationInfo(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    public ValidationInfo(boolean validationStatus, String falseValidationMessage) {
        this.validationStatus = validationStatus;
        this.falseValidationMessage = falseValidationMessage;
    }

    public boolean getValidationStatus() {
        return validationStatus;
    }

    public String getFalseValidationMessage() {
        return falseValidationMessage;
    }

}
