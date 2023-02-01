package duke;

import java.util.Scanner;

/**
 * UI of duke.Duke Application
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
     * Prompts user for the duke.command and reads the text entered by the user.
     * @return (full line) duke.command.
     */
    public String getCommand() {
        System.out.println("Enter your command: ");
        String inputLine = sc.nextLine();
        return inputLine;
    }

    public static void showError(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showOutput(String message) {
        System.out.println(message);
    }

}
