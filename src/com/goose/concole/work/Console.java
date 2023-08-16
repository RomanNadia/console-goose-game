package com.goose.concole.work;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import com.goose.concole.work.actions.Action;
import com.goose.concole.work.actions.FeedingAction;
import com.goose.concole.work.actions.WearingHatAction;
import com.goose.conection.bd.dao.*;
import com.goose.config.GooseConfig;
import com.goose.info.from.db.FoodsInfo;
import com.goose.info.from.db.HatsInfo;
import com.goose.models.Food;
import com.goose.models.Goose;
import com.goose.models.Hat;
import com.goose.models.Sessions;
import com.mysql.cj.Session;

public class Console {


    public Console() {
    }


    public Sessions chooseOrCreateSession() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Sessions session = new Sessions();

        System.out.println("Hello, happy user, please choose an action: \n 1 - create new session \n 2 - continue with " +
                "existed session");
        String input = scanner.nextLine();

        if(input.equals("1")) {
            System.out.println("Enter session name: ");               //cheak if sseasion with such name already exist
            String sessionName = scanner.nextLine();
            session.setSessionName(sessionName);
            saveNewSessionToBd(session);
        } else if(input.equals("2")) {
            session = chooseSession();
        }
        return session;
    }


    public Goose chooseCreateOrContinue(Sessions session) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Goose goose = new Goose();

        System.out.println("Hello, happy user, please choose an action: \n 1 - create new goose \n 2 - continue with " +
                "existed goose");
        String input1 = scanner.nextLine();

        if(input1.equals("1")) {
            System.out.println("Enter goose name: ");
            String inp_name = scanner.nextLine();                               // check if goose with such name exist
            System.out.println("Happy user, do you want default characteristics or custom: \n 1 - default \n 2 - custom");
            String input2 = scanner.nextLine();
            if(input2.equals("1")) {

                goose = new Goose(inp_name, GooseConfig.MAX_HUNGER, GooseConfig.MAX_HUNGER, GooseConfig.MAX_HYGIENE,
                        GooseConfig.MAX_HYGIENE, GooseConfig.MAX_SATISFACTION, GooseConfig.MAX_SATISFACTION,
                        GooseConfig.MAX_HEALTH, GooseConfig.MAX_HEALTH, session);
                goose.setDefaultHat();
                System.out.println("Goose is created! \n A description: " + goose.toString());
                saveNewGooseToBd(goose, session);
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
                goose.setDefaultHat();
                System.out.println("Goose is created! \n A description: " + goose.toString());
                saveNewGooseToBd(goose, session);
                return goose;

            } else {
                // redo
                System.out.println("Please try again: ");
                input2 = scanner.nextLine();

            }
        } else if(input1.equals("2")) {

            GooseDao gooseDao = new GooseDao();
            String selectedGooseName = chooseGoose(session);
            goose = gooseDao.getGooseByNameInSuchSession(selectedGooseName, session);
            goose.setGooseSession(session);
            System.out.println("Goose is found! \n a description: " + goose.toString());
            return goose;

        } else {
            //redo
            System.out.println("Please try again: ");
            input1 = scanner.nextLine();

        }
        return goose;
    }


    public Action chooseAction(Goose goose, Sessions session) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, happy user, please choose an action: \n 1 - feed goose \n 2 - wash goose " +
                "\n 3 - interact with goose \n 4 - choose hat for goose \n 5 - check goose state \n 6 - save and stop");
        String input = scanner.nextLine();


            if (input.equals("1")) {
                FeedingAction feedingAction = new FeedingAction(chooseFood());
                return feedingAction;
            } else if (input.equals("2")) {
                DetergentDao detergentDao = new DetergentDao();
            } else if (input.equals("3")) {

            } else if (input.equals("4")) {

                System.out.println("Happy user, please choose an action: \n 1 - chose existing hat \n 2 - custom a new hat");
                String inputHat = scanner.nextLine();

                if (inputHat.equals("1")) {
                    return new WearingHatAction(chooseHat(session));
                } else if (inputHat.equals("2")) {

                    System.out.println("Enter hat name: ");
                    String hatName = scanner.nextLine();
                    System.out.println("Enter nutrition: ");
                    String nutrition = scanner.nextLine();
                    System.out.println("Enter washing level: ");
                    String washingLevel = scanner.nextLine();
                    System.out.println("Enter satisfaction: ");
                    String satisfaction = scanner.nextLine();

                    Hat newHat = new Hat(hatName, Integer.parseInt(nutrition), Integer.parseInt(washingLevel),
                            Integer.parseInt(satisfaction));
                    saveHatToDB(newHat, session);
                    setId(newHat);
                    HatsInfo.addHatToHatsHashMap(newHat);

                    return new WearingHatAction(newHat);
                }
            } else if (input.equals("5")) {
                System.out.println(goose.toString());
            } else if (input.equals("6")) {
                saveGooseChangesToBd(goose);
                System.exit(0);
            } else {
                System.out.println("Please try again: ");
            }

        return new Action(); // Exeption or default action for next check
    }


    private String chooseGoose(Sessions session) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

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
        Scanner scanner = new Scanner(System.in);

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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, happy user, please choose a food:");
        HashMap<String, Food> foods = FoodsInfo.getFoods();

        foods.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = scanner.nextLine();

        return foods.get(input);
    }


    private Hat chooseHat(Sessions session) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, happy user, please choose an existing hat to wear:");
        HashMap<String, Hat> hats = HatsInfo.getHats(session);

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


    private void setId(Hat hat) throws SQLException, ClassNotFoundException {
        HatDao hatDao = HatDao.getHatDao();
        int id = hatDao.findHatIdByName(hat.getName());
        hat.setId(id);
    }


}
