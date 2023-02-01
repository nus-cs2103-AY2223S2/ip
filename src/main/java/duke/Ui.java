package duke;

import java.util.ArrayList;

/**
 * Handles interactions with the user.
 */
public class Ui {
    static String TOP_DIVIDER = "~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~\n" + "Duke's Response: \n";
    static String BOTTOM_DIVIDER = "\n~~~~~~~~~~~~~~~~o~~~~~~~~~~~~~~~~";
    private static String HELP_MESSAGE = "The available commands are: \n" +
            "1) list\n" +
            "2) bye\n" +
            "3) todo ________\n" +
            "4) deadline ______ /by ___________ (Note that the date has to be in YYYY-MM-DD format.\n" +
            "5) event ________ /from _______  /to _________\n" +
            "6) unmark ____\n" +
            "7) mark ______ \n" +
            "8) help\n" +
            "9) find ______ \n";

    public void listTasks(TaskList tasks) {
        System.out.println(TOP_DIVIDER);
        System.out.println("These are the current tasks: ");
        for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
            Task task = tasks.getTask(i);
            System.out.println(task.provideDetails());
        }
        System.out.println(BOTTOM_DIVIDER);
    }

    public void findTasks(ArrayList<Task> matchingTasks) {
        System.out.println(TOP_DIVIDER);
        System.out.println("These are the matching tasks: ");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            System.out.println(task.provideDetails() + "\n");
        }
        System.out.println(BOTTOM_DIVIDER);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("Hi, my name's Duke, how may I be of assistance today? :)");
    }

    /**
     * Responds to the user.
     * @param message The response message to be delivered to the user.
     */
    public void respond(String message) {
        System.out.println(TOP_DIVIDER + message + BOTTOM_DIVIDER);
    }


    /**
     * Displays a loading error message to the user.
     */
    public void showLoadingError() {
        System.out.println("The data from the existing file could not be loaded. A new file has been created. ");
    }

    /**
     *  Displays a command error message to the user.
     */
    public void showCommandError() {
        System.out.println("I'm sorry! Either the command you used was not valid, or it was incorrectly formatted. "
                + "To see what constitutes a valid command or format,"
                + " please type 'help'.");
    }

    /**
     *  Displays a help message to the user.
     */
    public void showHelpMessage() {
        System.out.println(HELP_MESSAGE);
    }









}
