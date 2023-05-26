import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.now();

        Console console = new Console();
        Goose goose = console.chooseCreateOrContinue();

        boolean continueProgram = true;

        while (continueProgram) {
            continueProgram = console.chooseAction(goose);
        }


    }
}
