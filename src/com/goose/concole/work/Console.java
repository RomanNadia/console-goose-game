package com.goose.concole.work;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import com.goose.concole.work.actions.Action;
import com.goose.concole.work.actions.FeedingAction;
import com.goose.concole.work.actions.WearingHatAction;
import com.goose.conection.bd.dao.*;
import com.goose.config.AplicationConfig;
import com.goose.info.from.db.FoodsInfo;
import com.goose.info.from.db.HatsInfo;
import com.goose.models.Food;
import com.goose.models.Goose;
import com.goose.models.Hat;
import com.goose.models.Sessions;
import com.goose.validation.*;
import com.goose.validation.validator.ActionValidator;
import com.goose.validation.validator.GooseValidator;
import com.goose.validation.validator.HatValidator;
import com.goose.validation.validator.SessionValidator;

public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private final HatValidator hatValidator = new HatValidator();
    private final SessionValidator sessionValidator = new SessionValidator();
    private final GooseValidator gooseValidator = new GooseValidator();
    private final ActionValidator actionValidator = new ActionValidator();


//    public Console() {
//    }


    public Sessions chooseOrCreateSession() throws SQLException, ClassNotFoundException {
        Sessions session = new Sessions();

        String input = getCorrectAction("Hello, happy user, please choose an action: \n 1 - create new session " +
                "\n 2 - continue with existed session", 2);

        if(input.equals("1")) {
            String sessionName = getCorrectNewSessionName("Enter session name");
            session.setSessionName(sessionName);
            saveNewSessionToBd(session);
        } else if(input.equals("2")) {
            session = chooseSession();
        }
        return session;
    }


    public Goose chooseCreateOrContinue(Sessions session) throws SQLException, ClassNotFoundException {
        Goose goose = new Goose();

        String input1 = getCorrectAction("Hello, happy user, please choose an action: \n 1 - create new goose " +
                "\n 2 - continue with existed goose", 2);

        if(input1.equals("1")) {
            String inp_name = getCorrectNewGooseName("Enter goose name", session);

            String input2 = getCorrectAction("Happy user, do you want default characteristics or custom: " +
                    "\n 1 - default \n 2 - custom", 2);
            if(input2.equals("1")) {

                goose = new Goose(inp_name, AplicationConfig.MAX_HUNGER, AplicationConfig.MAX_HUNGER, AplicationConfig.MAX_HYGIENE,
                        AplicationConfig.MAX_HYGIENE, AplicationConfig.MAX_SATISFACTION, AplicationConfig.MAX_SATISFACTION,
                        AplicationConfig.MAX_HEALTH, AplicationConfig.MAX_HEALTH, session);

                saveNewGooseToBd(goose, session);
                setIdForGooseFromBD(goose, session);

                System.out.println("Goose is created! \n A description: " + goose);
                return goose;

            } else if(input2.equals("2")) {

                System.out.println("Enter maximum of hunger: ");
                String custom_max_hunger = scanner.nextLine();
                System.out.println("Enter maximum of hygiene: ");
                String custom_max_hygiene = scanner.nextLine();
                System.out.println("Enter maximum of satisfaction: ");
                String custom_max_satisfaction = scanner.nextLine();
                System.out.println("Enter maximum of health: ");
                String custom_max_health = scanner.nextLine();
                goose = new Goose(inp_name, Integer.parseInt(custom_max_hunger), Integer.parseInt(custom_max_hunger),
                        Integer.parseInt(custom_max_hygiene), Integer.parseInt(custom_max_hygiene),
                        Integer.parseInt(custom_max_satisfaction), Integer.parseInt(custom_max_satisfaction),
                        Integer.parseInt(custom_max_health), Integer.parseInt(custom_max_health), session);
                System.out.println("Goose is created! \n A description: " + goose);
                saveNewGooseToBd(goose, session);
                return goose;

            }

        } else if(input1.equals("2")) {

            GooseDao gooseDao = new GooseDao();
            String selectedGooseName = chooseGoose(session);
            goose = gooseDao.getGooseByNameInSuchSession(selectedGooseName, session);
            goose.setGooseSession(session);
            System.out.println("Goose is found! \n a description: " + goose);
            return goose;

        }

        return goose;
    }


    public Action chooseAction(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {

        String input = getCorrectAction("Hello, happy user, please choose an action: \n 1 - feed goose " +
                "\n 2 - wash goose \n 3 - interact with goose \n 4 - choose hat for goose \n 5 - check goose state " +
                "\n 6 - save and stop", 6);

            if (input.equals("1")) {
                return new FeedingAction(chooseFood());
            } else if (input.equals("2")) {

            } else if (input.equals("3")) {

            } else if (input.equals("4")) {

                String inputHat = getCorrectAction("Happy user, please choose an action: \n 1 - chose existing hat " +
                        "\n 2 - custom a new hat \n 3 - bay hat in session", 3);

                if (inputHat.equals("1")) {
                    return new WearingHatAction(chooseAvailebleHat(goose.getGooseId()));
                } else if (inputHat.equals("2")) {

                    String hatName = getCorrectNewHatName("Enter hat name", session);

                    System.out.println("Every point costs " + AplicationConfig.COST_OF_HAT_POINS + " gooseCoins!");

                    String nutrition = getCorrectHatCharacteristic("Enter nutrition", goose, goose.getMaxHunger(),
                            AplicationConfig.HatNutritionCoefficient);
                    reduceGooseCoins(nutrition, goose);

                    String washingLevel = getCorrectHatCharacteristic("Enter washing level", goose,
                            goose.getMaxHygiene(), AplicationConfig.HatWashingLevelCoefficient);
                    reduceGooseCoins(washingLevel, goose);

                    String satisfaction = getCorrectHatCharacteristic("Enter satisfaction", goose,
                            goose.getMaxSatisfaction(), AplicationConfig.HatSatisfactionCoefficient);
                    reduceGooseCoins(satisfaction, goose);

                    Hat newHat = new Hat(hatName, Integer.parseInt(nutrition), Integer.parseInt(washingLevel),
                            Integer.parseInt(satisfaction));
                    saveHatToDB(newHat, session);
                    setIdForHat(newHat, session);
                    saveAsGooseAvalbleHatInBD(newHat, goose);
                    HatsInfo.addNewHatToAvailableHatsHashMap(newHat);
                    return new WearingHatAction(newHat);

                } else if (inputHat.equals("3")) {
                    Hat boughtHat = chooseSessionHat(session, goose.getGooseId());
                    saveAsGooseAvalbleHatInBD(boughtHat, goose);
                    HatsInfo.addSessionHatToAvailableHatsHashMap(boughtHat);
                    return new WearingHatAction(boughtHat);
                }
            } else if (input.equals("5")) {
                System.out.println(goose.toString());
            } else if (input.equals("6")) {
                saveGooseChangesToBd(goose);
                System.exit(0);
            }

        return new Action();
    }


    private String chooseGoose(Sessions session) throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose a goose:");
        GooseDao gooseDao = new GooseDao();
        HashMap<String, String> geeseNames = gooseDao.getGeeseNames(session);

        geeseNames.forEach((key, value) -> {
            System.out.print(value + " - " + key + "\n");
        });

        String input = scanner.nextLine();

        return geeseNames.get(input);
    }


    private Sessions chooseSession() throws SQLException, ClassNotFoundException {
       System.out.println("Hello, happy user, please choose a session:");
        SessionDao sessionDao = SessionDao.getSessionDao();
        HashMap<String, Sessions> sessionsNames = sessionDao.getSessionsNames();

        sessionsNames.forEach((key, value) -> {
            System.out.print(value.getSessionName() + " - " + key + "\n");
        });

        String input = scanner.nextLine();

        return sessionsNames.get(input);
    }


    private Food chooseFood() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose a food:");
        HashMap<String, Food> foods = FoodsInfo.getFoods();

        foods.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = scanner.nextLine();

        return foods.get(input);
    }


    private Hat chooseAvailebleHat(int gooseId) throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose an existing hat to wear:");
        HashMap<String, Hat> hats = HatsInfo.getAvailableHats(gooseId);

        hats.forEach((key, value) -> {
            if (key.equals("1")) {
                System.out.print("I do not want my goose wear ANY hat! - " + key + "\n");
            } else {
                System.out.print(value.getName() + " - " + key + "\n");
            }
        });

        String input = scanner.nextLine();

        return hats.get(input);
    }


    private Hat chooseSessionHat(Sessions session, int gooseId) throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose hat to bay:");
        HashMap<String, Hat> hats = HatsInfo.getSessionHats(session, gooseId);

        hats.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = scanner.nextLine();

        return hats.get(input);
    }


    private void saveNewSessionToBd(Sessions session) throws SQLException, ClassNotFoundException {
        SessionDao sessionDao = SessionDao.getSessionDao();
        sessionDao.insertSession(session);
    }


    private void saveNewGooseToBd(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        gooseDao.insertGoose(goose, session);
    }


    private void saveGooseChangesToBd(Goose goose) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        gooseDao.updateGoose(goose);
    }


    private void saveHatToDB(Hat hat, Sessions session) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        hatDao.insertHat(hat, session);
    }


    private void saveAsGooseAvalbleHatInBD(Hat hat, Goose goose) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        hatDao.insertGooseHat(hat, goose);
    }


    private void setIdForHat(Hat hat, Sessions session) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        int id = hatDao.findHatIdByName(hat.getName(), session);
        hat.setId(id);
    }

    
    private void setIdForGooseFromBD(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {
        GooseDao gooseDao = new GooseDao();
        int id = gooseDao.findGooseId(goose, session);
        goose.setGooseId(id);
    }
    
    
    private String getCorrectHatCharacteristic(String output, Goose goose, int maxValue, double coefficient) {
        String ifMistake = "";
        String input;
        ValidationInfo validationInfo;

        System.out.println("Your balance: " + goose.getGooseCoins() + " gooseCoins");
        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();

            validationInfo = hatValidator.validateHatCharacteristics(input, goose.getGooseCoins(), maxValue, coefficient);
            ifMistake = " again (DUDE, YOU ENTERED WRONG INPUT! " + validationInfo.getFalseValidationMessage() + ")";
        } while (!validationInfo.getValidationStatus());

        return input;
    }


    private void reduceGooseCoins(String characteristicPoints, Goose goose) {
        int cost = Integer.valueOf(characteristicPoints) * AplicationConfig.COST_OF_HAT_POINS;
        goose.setGooseCoins(goose.getGooseCoins() - cost);
    }

    private String getCorrectNewSessionName(String output) throws SQLException, ClassNotFoundException {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (entered name already exist)";
        } while (!sessionValidator.validateSessionName(input));

        return input;
    }

    private String getCorrectNewGooseName(String output, Sessions session) throws SQLException, ClassNotFoundException {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (entered name already exist)";
        } while (!gooseValidator.validateGooseName(input, session));

        return input;
    }


    private String getCorrectNewHatName(String output, Sessions session) throws SQLException, ClassNotFoundException {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (entered name already exist)";
        } while (!hatValidator.validateHatName(input, session));

        return input;
    }


    private String getCorrectAction(String output, int amountOfOptions) {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = "\n again (no such option as " + input + ")";
        } while (!actionValidator.validateActionInput(input, amountOfOptions));

        return input;
    }
}
