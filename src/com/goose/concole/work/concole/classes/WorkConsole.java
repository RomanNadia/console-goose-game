package com.goose.concole.work.concole.classes;

import com.goose.validation.validator.WorkActionValidator;

public class WorkConsole extends BaseConsole {
    private final WorkActionValidator workActionValidator = new WorkActionValidator();

    public int playWorkGame(int startGooseCoins) {
        String ifMistake = "";
        String input;
        int endGoosePoint = startGooseCoins;

        do {
            System.out.println("Your balance: " + endGoosePoint + " gooseCoins");
            System.out.println("You need to enter an upper case letter to get money (and e to escape)" + ifMistake + ": ");
            input = scanner.nextLine();

            if (workActionValidator.isStringUpperCaseLetter(input)) {
                endGoosePoint = endGoosePoint + 10;
            } else {
                ifMistake = " again (DUDE, YOU ENTERED WRONG INPUT! You should enter an upper case letter)";
            }

        } while (!input.equals("e"));

        return endGoosePoint;
    }

}
