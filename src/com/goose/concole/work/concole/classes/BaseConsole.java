package com.goose.concole.work.concole.classes;

import com.goose.validation.validator.ActionValidator;
import com.goose.validation.validator.WorkActionValidator;

import java.util.Scanner;

public class BaseConsole {
    protected Scanner scanner = new Scanner(System.in);
    private final ActionValidator actionValidator = new ActionValidator();

    protected String getCorrectAction(String output, int amountOfOptions) {
        String ifMistake = "";
        String input;
        if(!output.equals("")) {
            System.out.println(output);
        }

        do {
            System.out.println("Enter number" + ifMistake + ": ");
            input = scanner.nextLine();
            ifMistake = " again (no such option as " + input + ")";
        } while (!actionValidator.validateActionInput(input, amountOfOptions));

        return input;
    }

}
