package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class for the Ui object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class Ui {
    private static String logo =
            "      _     _            _                 \n" +
            "     | |   (_)          | |                \n" +
            "  ___| |__  _ _ __   ___| |__   __ _ _ __  \n" +
            " / __| '_ \\| | '_ \\ / __| '_ \\ / _` | '_ \\ \n" +
            " \\__ \\ | | | | | | | (__| | | | (_| | | | |\n" +
            " |___/_| |_|_|_| |_|\\___|_| |_|\\__,_|_| |_|\n";
    private final Scanner sc;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Print the welcome message when user first run Duke.
     */
    public static String showWelcome() {
        return "Hello from\n" + logo + "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Get the input typed by the user.
     *
     * @return String object containing the input by user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Show the error message.
     *
     * @param errorMsg
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Print the message when the user type "list" command.
     */
    public String showListMessage() {
        return "Here are the tasks in your list:";
    }

    /**
     * Print message when the user delete a task.
     *
     * @param task
     * @param tasksLen
     */
    public String showDeleteMessage(Task task, String tasksLen) {
        return "Noted. I've removed this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list.";
    }

    /**
     * Print the message when the user add a task.
     *
     * @param task
     * @param tasksLen
     */
    public String showAddTaskMsg(Task task, String tasksLen) {
        return "Got it. I've added this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list.";
    }

    /**
     * Print the message when the user type "exit" command.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print the message when the user mark a task as done.
     *
     * @param task
     */
    public String showMarkedMsg(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Print the message when the user mark a task as not done.
     *
     * @param task
     */
    public String showUnmarkedMsg(Task task) {
        return "Ok, I've marked this as not done yet:\n" + task;
    }

    /**
     * Print all the tasks containing the keyword requested by user.
     * @param tasklist
     */
    public String showRelevantTasks(ArrayList<Task> tasklist) {
        String currStr = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (int i = 0; i < tasklist.size(); i++) {
            currStr = currStr + counter+ "." + tasklist.get(i);
        }
        return currStr;
    }

    /**
     * Print the message when user type an unknown command.
     */
    public String showLoadingError() {
        return "No existing tasklist!";
    }

    /**
     * Print divider line.
     */
    public String showLine() {
        return "\n---------------------------------\n";
    }
}

