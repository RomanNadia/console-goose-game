package com.goose.validation.validator;

import com.goose.conection.bd.dao.GooseDao;
import com.goose.conection.bd.dao.HatDao;
import com.goose.config.AplicationConfig;
import com.goose.models.Goose;
import com.goose.models.Sessions;
import com.goose.validation.ValidationInfo;
import com.goose.validation.validator.Validator;

import java.sql.SQLException;

public class HatValidator extends Validator {

    public ValidationInfo validateHatCharacteristics(String characteristic, int gooseCoins, int maxValue,
                                                     double coefficient) {

        if(isStringInt(characteristic)) {
            int characteristicInt = Integer.valueOf(characteristic);
            if(maxValue * coefficient >= characteristicInt &&
                    isEnoughGooseCoins(characteristicInt, gooseCoins)) {
                return new ValidationInfo(true);
            } else {
                if(isEnoughGooseCoins(characteristicInt, gooseCoins)) {
                    return new ValidationInfo(false,"You cross the limit for characteristic");
                } else if(maxValue * coefficient >= characteristicInt) {
                    return new ValidationInfo(false,"You don`t have enough goose coins");
                } else {
                    return new ValidationInfo(false,"You cross the limit for " +
                            "characteristic and you don`t have enough goose coins");
                }
            }
        } else {
            return new ValidationInfo(false, characteristic + " is not an integer number");
        }
    }


    public boolean validateHatName(String name, Sessions session) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        if(hatDao.chekIfHatNameExistInSession(name, session)) {
            return false;
        } else {
            return true;
        }
    }


    private boolean isEnoughGooseCoins(int characteristic, int gooseCoins) {
        if(characteristic * AplicationConfig.COST_OF_HAT_POINS <= gooseCoins) {
            return true;
        } else {
            return false;
        }
    }


}
