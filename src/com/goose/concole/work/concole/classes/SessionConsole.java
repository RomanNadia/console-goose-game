package com.goose.concole.work.concole.classes;

import com.goose.conection.bd.dao.SessionDao;
import com.goose.models.Sessions;
import com.goose.validation.validator.SessionValidator;

import java.sql.SQLException;
import java.util.HashMap;

public class SessionConsole extends BaseConsole {
    private final SessionValidator sessionValidator = new SessionValidator();


    public boolean chekIfThereAreSessionToChoose() throws SQLException, ClassNotFoundException {
        HashMap<String, Sessions> sessionsNames = getSessionNames();
        return sessionsNames.isEmpty();
    }


    public Sessions chooseSession() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose a session:");

        HashMap<String, Sessions> sessionsNames = getSessionNames();

        sessionsNames.forEach((key, value) -> {
            System.out.print(value.getSessionName() + " - " + key + "\n");
        });

        String input = getCorrectAction("", sessionsNames.size());

        return sessionsNames.get(input);
    }


    public void createNewSessionByUser(Sessions session) throws SQLException, ClassNotFoundException {
        String sessionName = getCorrectNewSessionName("Enter session name");
        session.setSessionName(sessionName);
        saveNewSessionToBd(session);
    }


    private HashMap<String, Sessions> getSessionNames() throws SQLException, ClassNotFoundException {
        SessionDao sessionDao = SessionDao.getSessionDao();
        return sessionDao.getSessionsNames();
    }


    public void saveNewSessionToBd(Sessions session) throws SQLException, ClassNotFoundException {
        SessionDao sessionDao = SessionDao.getSessionDao();
        sessionDao.insertSession(session);
    }


    public String getCorrectNewSessionName(String output) throws SQLException, ClassNotFoundException {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (entered name already exist)";
        } while (!sessionValidator.validateSessionName(input));

        return input;
    }

}
