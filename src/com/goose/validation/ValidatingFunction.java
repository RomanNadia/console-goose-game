package com.goose.validation;

import com.goose.models.Goose;

@FunctionalInterface
public interface ValidatingFunction {
    ValidationInfo validate(String string, Goose goose);
}
