package com.goose.concole.work;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import com.goose.concole.work.actions.Action;
import com.goose.concole.work.actions.FeedingAction;
import com.goose.conection.bd.dao.DetergentDao;
import com.goose.conection.bd.dao.FoodDao;
import com.goose.conection.bd.dao.GooseDao;
import com.goose.config.GooseConfig;
import com.goose.models.Food;
import com.goose.models.Goose;

public class Console {


    public Console() {
    }

    public Goose chooseCreateOrContinue() {
        Scanner scanner = new Scanner(System.in);
        Goose goose = new Goose();

        System.out.println("Hello, happy user, please choose an action: \n 1 - create new goose \n 2 - continue with " +
                "existed goose");
        String input1 = scanner.nextLine();

        if(input1.equals("1")) {
            System.out.println("Enter goose name: ");
            String inp_name = scanner.nextLine(); // check if goose with such name exist
            System.out.println("Happy user, do you want default characteristics or custom: \n 1 - default \n 2 - custom");
            String input2 = scanner.nextLine();
            if(input2.equals("1")) {
                GooseConfig gc = new GooseConfig();
                goose = new Goose(inp_name, GooseConfig.MAX_HUNGER, GooseConfig.MAX_HUNGER, GooseConfig.MAX_HYGIENE,
                        GooseConfig.MAX_HYGIENE, GooseConfig.MAX_SATISFACTION, GooseConfig.MAX_SATISFACTION,
                        GooseConfig.MAX_HEALTH, GooseConfig.MAX_HEALTH);
                System.out.println("Goose is created! \n A description: " + goose.toString());
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
                        Integer.parseInt(custom_max_health), Integer.parseInt(custom_max_health)); //types
                System.out.println("Goose is created! \n A description: " + goose.toString());
                return goose;
            } else {
                System.out.println("Please try again: ");
                input2 = scanner.nextLine();
            }
//        } else if(input1.equals("2")) {
            //search and inizialize existing goose by name
//            System.out.println("Enter goose name: ");
//            String inp_name = scanner.nextLine();
//            Goose goose = new Goose(inp_name, );
//            System.out.println("Goose is found! \n a description: " + goose.toString());
//            return goose;
        } else {
            System.out.println("Please try again: ");
            input1 = scanner.nextLine();
        }
        return goose;
    }

    public Action chooseAction(Goose goose) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, happy user, please choose an action: \n 1 - feed goose \n 2 - wash goose " +
                "\n 3 - interact with goose \n 4 - choose hat for goose \n 5 - check goose state \n 6 - stop program");
        String input = scanner.nextLine();


 //       try {
            if (input.equals("1")) {
                FeedingAction feedingAction = new FeedingAction();
                feedingAction.setActionType(input);
                feedingAction.setFood(chooseFood());
                return feedingAction;
            } else if (input.equals("2")) {
                DetergentDao detergentDao = new DetergentDao();
            } else if (input.equals("3")) {

            } else if (input.equals("4")) {
                // saveToDB


                //Hat newHat = customNewHat;
                //hatAction.setHat(newHat);
                //return hatAction;
            } else if (input.equals("5")) {
                System.out.println(goose.toString());
            } else if (input.equals("6")) {

            } else {
                System.out.println("Please try again: ");
            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return new Action(); // Exeption or default action for next check
    }


    private Food chooseFood() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        FoodDao foodDao = new FoodDao();

        System.out.println("Hello, happy user, please choose a food:");
        HashMap<String, Food> foods = foodDao.getFoods();

        foods.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key);
        });

        String input = scanner.nextLine();

        return foods.get(input);
    }


//    private void saveStateToDB(Goose goose) {
//        GooseDao gooseDao = new GooseDao();
//        gooseDao.upsert(goose);
//
//    }



}
