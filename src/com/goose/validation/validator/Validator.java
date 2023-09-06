package com.goose.validation.validator;

import com.goose.conection.bd.dao.GooseDao;
import com.goose.config.AplicationConfig;
import com.goose.models.Goose;
import com.goose.models.Sessions;

import java.sql.SQLException;

public abstract class Validator {

    protected boolean isStringInt(String string) {
        return string.matches("\\d+");
    }

}
