import java.util.Scanner;

public class Console {
    final int MAX_HUNGER = 50;
    final int MAX_HYGIENE = 50;
    final int MAX_SATISFACTION = 50;
    final int MAX_HEALTH = 50; //maybe it should be in goose class?

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
                goose = new Goose(inp_name, MAX_HUNGER, MAX_HUNGER, MAX_HYGIENE, MAX_HYGIENE, MAX_SATISFACTION,
                        MAX_SATISFACTION, MAX_HEALTH, MAX_HEALTH);
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

    public boolean chooseAction(Goose goose) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, happy user, please choose an action: \n 1 - feed goose \n 2 - wash goose " +
                "\n 3 - interact with goose \n 4 - choose hat for goose \n 5 - check goose state \n 6 - stop program");
        String input = scanner.nextLine();

        if(input.equals("1")) {
            goose.feedGoose(5);
        } else if(input.equals("2")) {

        } else if(input.equals("3")) {

        } else if(input.equals("4")) {

        } else if(input.equals("5")) {
            System.out.println(goose.toString());
        } else if(input.equals("6")) {
            return false;
        } else {
            System.out.println("Please try again: ");
            input = scanner.nextLine();
        }
        return true;
    }


}
