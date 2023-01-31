package duke;

import java.util.Scanner;

/**
 * Handles interactions with the users.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        dukeGreeting();
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out the initial duke greeting when the program first runs.
     */
    public void dukeGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Checks if the user has another input.
     * @return True if the user has an input and false otherwise.
     */
    public boolean hasNextInput() {
        return scanner.hasNext();
    }

    /**
     * Reads user input.
     * @return User input.
     */
    public String userInput() {
        return scanner.nextLine();
    }

    /**
     * Terminates the program.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
