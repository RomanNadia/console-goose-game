package com.goose.validation.validator;

import com.goose.conection.bd.dao.SessionDao;
import com.goose.validation.validator.Validator;

import java.sql.SQLException;

public class SessionValidator extends Validator {

    public boolean validateSessionName(String name) throws SQLException, ClassNotFoundException {
        SessionDao sessionDao = SessionDao.getSessionDao();
        if(sessionDao.chekIfSessionNameExist(name)) {
            return false;
        } else {
            return true;
        }
    }

}
