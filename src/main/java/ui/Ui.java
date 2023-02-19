package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is the user interface of Duke.
 * Consists of the main bulk of processes for interacting with the user
 */
public class Ui {

    /** Logo of Duke */
    final String logo = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";

    /** Reader for faster scanning of input*/
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prints Duke's greeting to the user
     */
    public String greetUser() {
        return "Hello from\n" + logo + "\n Please wait while Duke loads your previous list...";
    }

    /**
     * Notifies user of successful loading of previous task list
     */
    public String notifySuccessfulLoad() {
        return "List successfully loaded! Please carry on with your Duke-y activities!";
    }

    /**
     * Gathers input commands from user
     *
     * @return String of user input
     */
    public String getUserInput() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return "Oh no! Duke didn't catch that!";
        }
    }

    /**
     * Prints exception message
     *
     * @param e exception thrown
     */
    public String printException(Exception e) {
        return e.getMessage();
    }
}
