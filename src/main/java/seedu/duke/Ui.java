package seedu.duke;

import java.util.Scanner;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private final String HELP_STR = "What can I help you with?";
    private final String BYE_STR = "Bye. Hope to see you again soon!";
    private final String TASK_ADDED = "I've added this task to your list:";

    private Scanner s;


    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HELP_STR);
    }

    /**
     * Creates a Scanner object to read user input.
     */
    public Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Returns the next command from user input.
     *
     * @return the next command from user input
     */
    public String getInput() {
        return (s.nextLine());
    }

    /**
     * Prints goodbye message and closes scanner.
     */
    public void showGoodbye() {
        System.out.println(BYE_STR);
        s.close();
    }

    /**
     * Prints message when a task is added to the list.
     *
     * @param task the task to be stored in list
     */
    public void showTaskAdded(Task task) {
        System.out.println(TASK_ADDED);
        System.out.println(task);
    }

    public void showLoadingError() {
        System.out.println("There was an error in loading your file!");
    }

    public void showGenericError() {
        System.out.println("Error!");
    }
}
