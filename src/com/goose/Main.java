package com.goose;

import com.goose.concole.work.actions.Action;
import com.goose.models.Goose;

//import java.io.Console;
import java.sql.SQLException;
import java.util.Date;
import com.goose.concole.work.concole.classes.MainConsole;
import com.goose.models.Sessions;
import com.goose.services.GooseService;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        MainConsole console = new MainConsole();
        Sessions session = console.chooseOrCreateSession();
        Goose goose = console.chooseCreateOrContinue(session);
        GooseService gooseService = new GooseService();

        Date date = new Date();

        goose.setLastUpdateTime(date.getTime());

        boolean continueProgram = true;


        while (continueProgram) {
            goose.updateCharacteristics();
            Action action = console.chooseAction(goose, session);
            gooseService.doAction(action, goose);
        }


    }
}
