package com.goose.validation.validator;

public class ActionValidator extends Validator {

    public boolean validateActionInput(String input, int amountOfOptions) {
        if(isStringInt(input)) {
            int intInput = Integer.valueOf(input);
            if(intInput <= amountOfOptions && intInput > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
