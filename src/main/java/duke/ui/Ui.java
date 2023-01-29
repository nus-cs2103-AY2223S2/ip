package duke.ui;

import duke.task.Task;

import java.util.Scanner;

/**
 * Class for the Ui object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
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
    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "Hello! I'm Duke\nWhat can I do for you?");
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
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Print the message when the user type "list" command.
     */
    public void showListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Print message when the user delete a task.
     *
     * @param task
     * @param tasksLen
     */
    public void showDeleteMessage(Task task, String tasksLen) {
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + tasksLen + " tasks in the list.");

    }

    /**
     * Print the message when the user add a task.
     *
     * @param task
     * @param tasksLen
     */
    public void showAddTaskMsg(Task task, String tasksLen) {
        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasksLen + " tasks in the list.");
    }

    /**
     * Print the message when the user type "exit" command.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the message when the user mark a task as done.
     *
     * @param task
     */
    public void showMarkedMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Print the message when the user mark a task as not done.
     *
     * @param task
     */
    public void showUnmarkedMsg(Task task) {
        System.out.println("Ok, I've marked this as not done yet:\n" + task);
    }

    /**
     * Print the message when user type an unknown command.
     */
    public void showLoadingError() {
        System.out.println("No existing tasklist!");
    }

    /**
     * Print divider line.
     */
    public void showLine() {
        System.out.println("\n---------------------------------\n");
    }
}

