package com.goose.validator;

import com.goose.models.Goose;

public class Validator {
    private final double HatCharacteristicsCoefficient = 0.2;

    public Validator() {
    }

//    public boolean validateHatName() {
//
//    }

    public boolean validateHatCharacteristics(String characteristic, Goose goose) {
        if(isStringInt(characteristic)) {
            if(goose.getMaxHunger() * HatCharacteristicsCoefficient >= Integer.valueOf(characteristic)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    private boolean isStringInt(String string) {
        return string.matches("\\d+");
    }

    public double getHatCharacteristicsCoefficient() {
        return HatCharacteristicsCoefficient;
    }
}
