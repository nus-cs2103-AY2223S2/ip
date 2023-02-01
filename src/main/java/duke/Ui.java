package duke;

import java.util.Scanner;

/**
 * UI of Duke Application
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "How may I be of service to you? \n");
    }

    /**
     * Prompts user for the command and reads the text entered by the user.
     * @return (full line) string command
     */
    public String getCommand() {
        System.out.println("Enter your command: ");
        String inputLine = sc.nextLine();
        return inputLine;
    }

    /**
     * Prints exception message
     * @param exceptionMessage
     */
    public static void showError(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    /**
     * Prints goodbye.
     */
    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
