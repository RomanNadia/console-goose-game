import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime startTime = LocalDateTime.now();

        Console console = new Console();
        Goose goose = console.chooseCreateOrContinue();

        boolean continueProgram = true;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                goose.starve();
            }
        }, 0, 10);


        while (continueProgram) {
            continueProgram = console.chooseAction(goose);

        }



    }
}
