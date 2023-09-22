package com.goose.validation.validator;

public class WorkActionValidator extends Validator {

    public boolean isStringUpperCaseLetter(String string) {
        return string.matches("[A-Z]");
    }
}
