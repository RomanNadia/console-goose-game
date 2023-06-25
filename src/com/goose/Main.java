package com.goose;

import com.goose.concole.work.actions.Action;
import com.goose.models.Goose;

//import java.io.Console;
import java.sql.SQLException;
import java.util.Date;
import com.goose.concole.work.Console;
import com.goose.services.GooseService;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        try {


            //con.close(); //where?

        } catch (Exception e) {
            System.out.println(e);
        }


        Console console = new Console();
        Goose goose = console.chooseCreateOrContinue();
        GooseService gooseService = new GooseService();

        Date date = new Date();
        long timeMilli = date.getTime();

        boolean continueProgram = true;


        while (continueProgram) {
            goose.updateCharacteristics(timeMilli); //where update (in console)?
            timeMilli = date.getTime();
            Action action = console.chooseAction(goose);
            gooseService.doAction(action, goose);

        }


    }
}
