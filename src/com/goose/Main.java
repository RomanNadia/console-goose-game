package com.goose;

import com.goose.concole.work.Action;
import com.goose.models.Goose;

//import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import com.goose.concole.work.Console;


public class Main {
    public static void main(String[] args) throws Exception {


        try {


            //con.close(); //where?

        } catch (Exception e) {
            System.out.println(e);
        }


        Console console = new Console();
        Goose goose = console.chooseCreateOrContinue();


        Date date = new Date();
        long timeMilli = date.getTime();

        boolean continueProgram = true;

        while (continueProgram) {
            goose.updateCharacteristics(timeMilli); //where update (in console)?
            timeMilli = date.getTime();
            Action action = console.chooseAction(goose);
            //gooseServise.doActtion(action, goose);

        }


    }
}
