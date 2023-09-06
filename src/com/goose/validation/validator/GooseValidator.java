package com.goose.validation.validator;

import com.goose.conection.bd.dao.GooseDao;
import com.goose.models.Sessions;

import java.sql.SQLException;

public class GooseValidator extends Validator {

    public boolean validateGooseName(String name, Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        if(gooseDao.chekIfGooseNameExist(name, session)) {
            return false;
        } else {
            return true;
        }
    }

}
