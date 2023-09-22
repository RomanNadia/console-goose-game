package com.goose.concole.work.concole.classes;

import com.goose.concole.work.actions.Action;
import com.goose.concole.work.actions.WearingHatAction;
import com.goose.conection.bd.dao.HatDao;
import com.goose.config.AplicationConfig;
import com.goose.info.from.db.HatsInfo;
import com.goose.models.Goose;
import com.goose.models.Hat;
import com.goose.models.Sessions;
import com.goose.validation.ValidationInfo;
import com.goose.validation.validator.HatValidator;

import java.sql.SQLException;
import java.util.HashMap;

public class HatConsole extends BaseConsole {
    private final HatValidator hatValidator = new HatValidator();


    public Hat chooseAvailebleHat(int gooseId) throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose an existing hat to wear:");
        HashMap<String, Hat> hats = HatsInfo.getAvailableHats(gooseId);

        hats.forEach((key, value) -> {
            if (key.equals("1")) {
                System.out.print("I do not want my goose wear ANY hat! - " + key + "\n");
            } else {
                System.out.print(value.getName() + " - " + key + "\n");
            }
        });

        String input = getCorrectAction("", hats.size());

        return hats.get(input);
    }


    public WearingHatAction chooseHatAction(Goose goose, Sessions session, GooseConsole gooseConsole)
            throws SQLException, ClassNotFoundException {

        String inputHat = getCorrectAction("Happy user, please choose an action: \n 1 - chose existing hat " +
                "\n 2 - custom a new hat \n 3 - bay hat in session", 3);

        if (inputHat.equals("1")) {
            return new WearingHatAction(chooseAvailebleHat(goose.getGooseId()));
        } else if (inputHat.equals("2")) {
            Hat newHat = customNewHat(goose, session, gooseConsole);
            return new WearingHatAction(newHat);
        } else if (inputHat.equals("3") && chekIfThereAreAvailableHatToBay(session, goose.getGooseId())) {
            System.out.println("There are no available hat to bay. Please choose an existing one.");
            return new WearingHatAction(chooseAvailebleHat(goose.getGooseId()));
        } else {
            Hat boughtHat = chooseAvailableHatsToBay(session, goose.getGooseId());
            saveAsGooseAvalbleHatInBD(boughtHat, goose);
            HatsInfo.addAvailableHatsToBayToAvailableHatsHashMap(boughtHat);
            return new WearingHatAction(boughtHat);
        }
    }


    public boolean chekIfThereAreAvailableHatToBay(Sessions session, int gooseId)
            throws SQLException, ClassNotFoundException {
        HashMap<String, Hat> hats = HatsInfo.getAvailableHatsToBay(session, gooseId);
        return hats.isEmpty();
    }


    private Hat customNewHat(Goose goose, Sessions session, GooseConsole gooseConsole)
            throws SQLException, ClassNotFoundException {
        String hatName = getCorrectNewHatName("Enter hat name", session);

        System.out.println("Every point costs " + AplicationConfig.COST_OF_HAT_POINS + " gooseCoins!");

        String nutrition = getCorrectHatCharacteristic("Enter nutrition", goose, goose.getMaxHunger(),
                AplicationConfig.HatNutritionCoefficient);
        gooseConsole.reduceGooseCoins(nutrition, goose); //service method

        String washingLevel = getCorrectHatCharacteristic("Enter washing level", goose,
                goose.getMaxHygiene(), AplicationConfig.HatWashingLevelCoefficient);
        gooseConsole.reduceGooseCoins(washingLevel, goose);

        String satisfaction = getCorrectHatCharacteristic("Enter satisfaction", goose,
                goose.getMaxSatisfaction(), AplicationConfig.HatSatisfactionCoefficient);
        gooseConsole.reduceGooseCoins(satisfaction, goose);

        Hat newHat = new Hat(hatName, Integer.parseInt(nutrition), Integer.parseInt(washingLevel),
                Integer.parseInt(satisfaction));
        saveHatToDB(newHat, session);
        setIdForHat(newHat, session);
        saveAsGooseAvalbleHatInBD(newHat, goose);
        HatsInfo.addNewHatToAvailableHatsHashMap(newHat);
        return newHat;
    }


    public Hat chooseAvailableHatsToBay(Sessions session, int gooseId) throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose hat to bay:");
        HashMap<String, Hat> hats = HatsInfo.getAvailableHatsToBay(session, gooseId);

        hats.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = getCorrectAction("", hats.size());

        return hats.get(input);
    }


    public void saveHatToDB(Hat hat, Sessions session) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        hatDao.insertHat(hat, session);
    }


    public void saveAsGooseAvalbleHatInBD(Hat hat, Goose goose) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        hatDao.insertGooseHat(hat, goose);
    }


    public void setIdForHat(Hat hat, Sessions session) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        int id = hatDao.findHatIdByName(hat.getName(), session);
        hat.setId(id);
    }


    public String getCorrectHatCharacteristic(String output, Goose goose, int maxValue, double coefficient) {
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


    public String getCorrectNewHatName(String output, Sessions session) throws SQLException, ClassNotFoundException {
        String ifMistake = "";
        String input;

        do {
            System.out.println(output + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (entered name already exist)";
        } while (!hatValidator.validateHatName(input, session));

        return input;
    }

}
