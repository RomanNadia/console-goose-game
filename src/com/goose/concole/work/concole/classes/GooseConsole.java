package com.goose.concole.work.concole.classes;

import com.goose.conection.bd.dao.GooseDao;
import com.goose.config.AplicationConfig;
import com.goose.models.Goose;
import com.goose.models.Sessions;
import com.goose.validation.validator.GooseValidator;

import java.sql.SQLException;
import java.util.HashMap;

public class GooseConsole extends BaseConsole {
    private final GooseValidator gooseValidator = new GooseValidator();


    public String chooseGoose(Sessions session) throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose a goose:");
        HashMap<String, String> geeseNames = getGeeseNames(session);

        geeseNames.forEach((key, value) -> {
            System.out.print(value + " - " + key + "\n");
        });

        String input = getCorrectAction("", geeseNames.size());

        return geeseNames.get(input);
    }


    private HashMap<String, String> getGeeseNames(Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        return gooseDao.getGeeseNames(session);
    }


    public boolean chekIfThereAreGooseToChoose(Sessions session) throws SQLException, ClassNotFoundException {
        HashMap<String, String> geeseNames = getGeeseNames(session);
        return geeseNames.isEmpty();
    }


    public Goose createDefaultOrCustomGoose(Sessions session) throws SQLException, ClassNotFoundException {
        String inp_name = getCorrectNewGooseName("Enter goose name", session);
        Goose goose;

        String input2 = getCorrectAction("Happy user, do you want default characteristics or custom: " +
                "\n 1 - default \n 2 - custom", 2);
        if(input2.equals("1")) {
            goose = createAndSaveDefaultGoose(inp_name, session);
            return goose;
        } else {
            goose = createAndSaveCustomGoose(inp_name, session);
            return goose;
        }
    }


    private Goose createAndSaveDefaultGoose(String inp_name, Sessions session) throws SQLException, ClassNotFoundException {
        Goose goose = new Goose(inp_name, AplicationConfig.MAX_HUNGER, AplicationConfig.MAX_HUNGER, AplicationConfig.MAX_HYGIENE,
                AplicationConfig.MAX_HYGIENE, AplicationConfig.MAX_SATISFACTION, AplicationConfig.MAX_SATISFACTION,
                AplicationConfig.MAX_HEALTH, AplicationConfig.MAX_HEALTH, session);

        saveNewGooseToBd(goose, session);
        setIdForGooseFromBD(goose, session);
        System.out.println("Goose is created! \n A description: " + goose);
        return goose;
    }


    private Goose createAndSaveCustomGoose(String inp_name, Sessions session) throws SQLException, ClassNotFoundException {
        System.out.println("Enter maximum of hunger: ");
        String custom_max_hunger = scanner.nextLine();
        System.out.println("Enter maximum of hygiene: ");
        String custom_max_hygiene = scanner.nextLine();
        System.out.println("Enter maximum of satisfaction: ");
        String custom_max_satisfaction = scanner.nextLine();
        System.out.println("Enter maximum of health: ");
        String custom_max_health = scanner.nextLine();
        Goose goose = new Goose(inp_name, Integer.parseInt(custom_max_hunger), Integer.parseInt(custom_max_hunger),
                Integer.parseInt(custom_max_hygiene), Integer.parseInt(custom_max_hygiene),
                Integer.parseInt(custom_max_satisfaction), Integer.parseInt(custom_max_satisfaction),
                Integer.parseInt(custom_max_health), Integer.parseInt(custom_max_health), session);
        System.out.println("Goose is created! \n A description: " + goose);
        saveNewGooseToBd(goose, session);
        setIdForGooseFromBD(goose, session);
        return goose;
    }


    public Goose createNewGooseByUser(Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        String selectedGooseName = chooseGoose(session);
        Goose goose = gooseDao.getGooseByNameInSuchSession(selectedGooseName, session);
        goose.setGooseSession(session);
        System.out.println("Goose is found! \n a description: " + goose);
        return goose;
    }


    public void saveNewGooseToBd(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        gooseDao.insertGoose(goose, session);
    }


    public void saveGooseChangesToBd(Goose goose) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        gooseDao.updateGoose(goose);
    }


    public void setIdForGooseFromBD(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        int id = gooseDao.findGooseId(goose, session);
        goose.setGooseId(id);
    }


    public void reduceGooseCoins(String characteristicPoints, Goose goose) {
        int cost = Integer.valueOf(characteristicPoints) * AplicationConfig.COST_OF_HAT_POINS;
        goose.setGooseCoins(goose.getGooseCoins() - cost);
    }


    public String getCorrectNewGooseName(String output, Sessions session) throws SQLException, ClassNotFoundException {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (entered name already exist)";
        } while (!gooseValidator.validateGooseName(input, session));

        return input;
    }
}
