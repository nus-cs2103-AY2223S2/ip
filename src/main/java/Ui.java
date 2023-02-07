import java.awt.*;
import java.util.Scanner;

public class Ui {
    private Scanner scan;
    private final String DIVIDER = "____________________________________________________________";
    private final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() {
        scan = new Scanner(System.in);
    }

    public String readCommand() {
        return scan.nextLine();
    }

    public void showWelcome() {
        String welcomeMsg = LOGO + "\nHello! I'm Duke\nWhat can I do for you?";
        dukeSpeak(welcomeMsg);
    }
    public void showLoadingError() {
        dukeSpeak("Unable to load tasks.");
    }

    public void dukeSpeak(String message) {
        String printStr = DIVIDER + "\n" + message + "\n" + DIVIDER;
        System.out.println(printStr);
    }

    public void close() {
        scan.close();
    }
}
