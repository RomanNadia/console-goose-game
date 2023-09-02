package com.goose.validation.validator;

import com.goose.models.Goose;
import com.goose.validation.ValidationInfo;
import com.goose.validation.validator.Validator;

public class HatValidator extends Validator {

    private final static double HatCharacteristicsCoefficient = 0.2;

    public ValidationInfo validateHatCharacteristics(String characteristic, Goose goose) {

        if(isStringInt(characteristic)) {
            int characteristicInt = Integer.valueOf(characteristic);
            if(goose.getMaxHunger() * HatCharacteristicsCoefficient >= characteristicInt &&
                    isEnoughGooseCoins(characteristicInt, goose)) {
                return new ValidationInfo(true);
            } else {
                if(isEnoughGooseCoins(characteristicInt, goose)) {
                    return new ValidationInfo(false,"You cross the limit for characteristic");
                } else if(goose.getMaxHunger() * HatCharacteristicsCoefficient >= characteristicInt) {
                    return new ValidationInfo(false,"You dont have enough goose coins");
                } else {
                    return new ValidationInfo(false,"You cross the limit for " +
                            "characteristic and you dont have enough goose coins");
                }
            }
        } else {
            return new ValidationInfo(false, characteristic + " is not an integer number");
        }
    }

    public double getHatCharacteristicsCoefficient() {
        return HatCharacteristicsCoefficient;
    }
}
