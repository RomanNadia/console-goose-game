package com.goose.validation.validator;

import com.goose.conection.bd.dao.GooseDao;
import com.goose.config.AplicationConfig;
import com.goose.models.Goose;
import com.goose.models.Sessions;

import java.sql.SQLException;

public abstract class Validator {

    protected boolean isEnoughGooseCoins(int characteristic, Goose goose) {
        if(characteristic * AplicationConfig.COST_OF_HAT_POINS <= goose.getGooseCoins()) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isStringInt(String string) {
        return string.matches("\\d+");
    }

}
