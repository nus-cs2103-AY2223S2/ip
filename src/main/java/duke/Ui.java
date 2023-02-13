package duke;

import java.util.Scanner;

/**
 * Class that encapsulates UI interaction with user
 */
public class Ui {
    private Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Method to read commands interactively
     * @return command that is inputted by user
     */
    public String readCommand() {
        System.out.println("Enter your command!");
        return this.reader.nextLine();
    }

    /**
     * Prints welcome message
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "Hello! I'm duke.Duke\nWhat can I do for you?";
    }

    /**
     * Prints a goodbye message
     */
    public String showBye() {
        return "Exiting...\n Bye. Hope to see you again soon!";
    }

    /**
     * Prints wrong date format error message
     */
    public static String wrongDateFormat() {
        return "Please write your deadline in this format: YYYY-MM-DD HH:mm ";
    }

}
