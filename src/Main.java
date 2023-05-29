import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Console console = new Console();
        Goose goose = console.chooseCreateOrContinue();


        Date date = new Date();
        long timeMilli = date.getTime();

        boolean continueProgram = true;

        while (continueProgram) {
            goose.updateCharacteristics(timeMilli); //where update (in console)?
            timeMilli = date.getTime();
            continueProgram = console.chooseAction(goose);
        }



    }
}
