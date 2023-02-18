package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is the user interface of Duke.
 * Consists of the main bulk of processes for interacting with the user
 */
public class Ui {

    /** Reader for faster scanning of input*/
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /** Logo of Duke */
    final String logo = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints Duke's greeting to the user
     */
    public void greetUser() {
        printResponse("Hello from\n" + logo + "\n Please wait while Duke loads your previous list...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Notifies user of successful loading of previous task list
     */
    public void notifySuccessfulLoad() {
        printResponse("List successfully loaded! Please carry on with your Duke-y activities!");
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
            printResponse("Oh no! Duke didn't catch that!");
            return getUserInput();
        }
    }

    /**
     * Standardises Duke's response printing format by enclosing the response within 2 horizontal lines
     * 
     * @param response Duke's response to User
     */
    public void printResponse(String response) {
        System.out.println("\n----------------------------------\n");
        System.out.println(response);
        System.out.println("\n----------------------------------\n");
    }

    /**
     * Prints exception message
     * 
     * @param e exception thrown
     */
    public void printException(Exception e) {
        printResponse(e.getMessage());
    }

    public void endSession() {
        printResponse("Bye! Hope you enjoyed using Duke! \n Your list awaits your return!");
        try {
            br.close();
        } catch (IOException e) {
            printResponse("NO! U ALREADY SAID BYE!\nLaunch Duke again if you want to talk to Duke again! BYE!");
        }
    }
}
