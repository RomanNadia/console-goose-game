package com.goose.validator;

import com.goose.config.GooseConfig;
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
            int characteristicInt = Integer.valueOf(characteristic);
            if(goose.getMaxHunger() * HatCharacteristicsCoefficient >= characteristicInt &&
                    isEnoughGooseCoins(characteristicInt, goose)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;  //exeptions
        }
    }


    private boolean isEnoughGooseCoins(int characteristic, Goose goose) {
        if(characteristic * GooseConfig.COST_OF_HAT_POINS <= goose.getGooseCoins()) {
            return true;
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
