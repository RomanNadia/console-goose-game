package com.goose.concole.work.concole.classes;

import java.sql.SQLException;

import com.goose.concole.work.actions.*;
import com.goose.models.Goose;
import com.goose.models.Sessions;

public class MainConsole extends BaseConsole {
    private final WorkConsole workConsole = new WorkConsole();
    private final SessionConsole sessionConsole = new SessionConsole();
    private final GooseConsole gooseConsole = new GooseConsole();
    private final FoodConsole foodConsole = new FoodConsole();
    private final DetergentConsole detergentConsole = new DetergentConsole();
    private final ActivityConsole activityConsole = new ActivityConsole();
    private final HatConsole hatConsole = new HatConsole();

    public Sessions chooseOrCreateSession() throws SQLException, ClassNotFoundException {
        Sessions session = new Sessions();

        String input = getCorrectAction("Hello, happy user, please choose an action: \n 1 - create new session " +
                "\n 2 - continue with existed session", 2);

        if(input.equals("1")) {
            sessionConsole.createNewSessionByUser(session);
        } else if(input.equals("2") && sessionConsole.chekIfThereAreSessionToChoose()) {
            System.out.println("There no session to choose. Please create one.");
            sessionConsole.createNewSessionByUser(session);
        } else {
            session = sessionConsole.chooseSession();
        }
        return session;
    }


    public Goose chooseCreateOrContinue(Sessions session) throws SQLException, ClassNotFoundException {
        Goose goose;

        String input1 = getCorrectAction("Hello, happy user, please choose an action: \n 1 - create new goose " +
                "\n 2 - continue with existed goose", 2);

        if(input1.equals("1")) {
            goose = gooseConsole.createDefaultOrCustomGoose(session);
            return goose;
        } else if(input1.equals("2") && gooseConsole.chekIfThereAreGooseToChoose(session)) {
            System.out.println("There are no goose to choose. Please create one.");
            goose = gooseConsole.createDefaultOrCustomGoose(session);
            return goose;
        } else {
            goose = gooseConsole.createNewGooseByUser(session);
            return goose;
        }

    }


    public Action chooseAction(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {

        String input = getCorrectAction("Hello, happy user, please choose an action: \n 1 - feed goose " +
                "\n 2 - wash goose \n 3 - interact with goose \n 4 - choose hat for goose \n 5 - check goose state " +
                "\n 6 - work \n 7 - save and stop", 7);

            if (input.equals("1")) {
                return new FeedingAction(foodConsole.chooseFood());
            } else if (input.equals("2")) {
                return new WashingAction(detergentConsole.chooseDetergent());
            } else if (input.equals("3")) {
                return new InteractingAction(activityConsole.chooseActivity());
            } else if (input.equals("4")) {
                return hatConsole.chooseHatAction(goose, session, gooseConsole);
            } else if (input.equals("5")) {
                System.out.println(goose.toString()); //return action and in gooseService apply some console to print
            } else if (input.equals("6")) {
                return new WorkAction(workConsole.playWorkGame(goose.getGooseCoins()));
            } else if (input.equals("7")) {
                goose.updateCharacteristics();
                gooseConsole.saveGooseChangesToBd(goose);
                System.exit(0);
            }

        return new Action();
    }

}
