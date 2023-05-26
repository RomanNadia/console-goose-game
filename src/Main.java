import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_HUNGER = 50;
        final int MAX_HYGIENE = 50;
        final int MAX_SATISFACTION = 50;
        final int MAX_HEALTH = 50; //maybe it should be in goose class?
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, happy user, please choose an action: \n 1 - create new goose \n 2 - continue with " +
                "existed goose");
        String input_1 = scanner.nextLine();

        if(input_1.equals("1")) {
            System.out.println("Enter goose name: ");
            String inp_name = scanner.nextLine(); // check if goose with such name exist
            System.out.println("Happy user, do you want default characteristics or custom: \n 1 - default \n 2 - custom");
            String input_2 = scanner.nextLine();
            if(input_2.equals("1")) {
                Goose goose = new Goose(inp_name, MAX_HUNGER, MAX_HUNGER, MAX_HYGIENE, MAX_HYGIENE, MAX_SATISFACTION,
                        MAX_SATISFACTION, MAX_HEALTH, MAX_HEALTH);
                System.out.println("Goose is created! \n A description: " + goose.toString());
            } else if(input_2.equals("2")) {
                System.out.println("Enter max_hunger: ");
                String custom_max_hunger = scanner.nextLine();
                System.out.println("Enter MAX_HYGIENE: ");
                String custom_max_hygiene = scanner.nextLine();
                System.out.println("Enter MAX_SATISFACTION: ");
                String custom_max_satisfaction = scanner.nextLine();
                System.out.println("Enter MAX_HEALTH: ");
                String custom_max_health = scanner.nextLine();
                Goose goose = new Goose(inp_name, Integer.parseInt(custom_max_hunger), Integer.parseInt(custom_max_hunger),
                        Integer.parseInt(custom_max_hygiene), Integer.parseInt(custom_max_hygiene),
                        Integer.parseInt(custom_max_satisfaction), Integer.parseInt(custom_max_satisfaction),
                        Integer.parseInt(custom_max_health), Integer.parseInt(custom_max_health)); //types
                System.out.println("Goose is created! \n A description: " + goose.toString());
            } else {
                System.out.println("Please try again: ");
                input_2 = scanner.nextLine();
            }
//        } else if(input_1.equals("2")) {
            //search and inizialize existing goose by name
//            System.out.println("Enter goose name: ");
//            String inp_name = scanner.nextLine();
//            Goose goose = new Goose(inp_name, );
//            System.out.println("Goose is found! \n a description: " + goose.toString());
        } else {
            System.out.println("Please try again: ");
            input_1 = scanner.nextLine();
        }

        

        while (true) {
            System.out.println("Hello, happy user, please choose an action: \n 1 - feed goose \n 2 - wash goose " +
                    "\n 3 - interact with goose \n 4 - choose hat for goose");
            String input_3 = scanner.nextLine();
            if(input_3.equals("1")) {
                user_action.feedGoose(goose, 5);
            } else if(input_3.equals("2")) {

            } else if(input_3.equals("3")) {

            } else if(input_3.equals("4")) {

            }
        }




    }
}
