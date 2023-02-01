package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents Duke's interaction with user.
 *
 * @author Karen
 */
public class Ui {

    private static final String STR = "------------------------------------------------------------";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    /**
     * Initialises new instance of Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the next command input by user.
     */
    public String nextInput() {
        return this.sc.nextLine();
    }

    /**
     * Prints a welcome message.
     */
    public void welcomeResponse() {
        System.out.println(LOGO + "Hello! I'm Duke\n");
    }

    /**
     * Prints a message indicating load from memory was successful.
     */
    public void successfulLoadResponse() {
        System.out.println("I've successfully retrieved your past task list!");
    }

    /**
     * Prints a message asking user for a Task.
     */
    public void askForTaskResponse() {
        System.out.println("What can I do for you today?");
    }

    /**
     * Prints the list of Tasks.
     *
     * @param tasks The array of tasks.
     */
    public void listTaskResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            printResponse("You have 0 task to complete at the moment!");
        } else {
            printResponse("Here are the task in your list: \n" + tasks.toStringList() + "\nYou have " + tasks.getSize()
                    + " tasks in the list.");
        }
    }

    /**
     * Prints the sublist of Tasks.
     *
     * @param tasks The array of tasks.
     */
    public void subListTaskResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            printResponse("You have 0 matching task!");
        } else {
            printResponse("Here are the matching tasks in your list: \n" + tasks.toStringList() + "\nYou have "
                    + tasks.getSize() + " matching tasks in the list.");
        }
    }

    /**
     * Prints a message indicating to user selected task was successfully marked.
     *
     * @param t The selected task.
     */
    public void markTaskResponse(Task t) {
        printResponse("Nice! I've marked this task as done: \n " + t);
    }

    /**
     * Prints a message indicating to user selected task was successfully unmarked.
     *
     * @param t The selected task.
     */
    public void unmarkTaskResponse(Task t) {
        printResponse("OK, I've marked this task as not done yet \n" + t);
    }

    /**
     * Prints a message indicating to user selected task was successfully deleted.
     *
     * @param t The selected task.
     * @param lst The array of tasks.
     */
    public void deleteTaskResponse(Task t, TaskList lst) {
        printResponse("Noted. I've removed this task: \n" + t + "\nNow you have " + lst.getSize() + " "
                + "tasks in the list.");
    }

    /**
     * Prints a message indicating to user the new task was successfully added.
     *
     * @param t The new task.
     * @param lst The array of tasks.
     */
    public void addTaskResponse(Task t, TaskList lst) {
        printResponse("Got it. I've added this task: \n" + t + "\nNow you have " + lst.getSize()
                + " tasks in the list.");
    }

    /**
     * Prints a message indicating to user that Duke has exited program.
     */
    public void exitResponse() {
        printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message indicating to user that a task has not been selected.
     */
    public void taskNotChosenErrorMessage() {
        printResponse("OPPS! You have not selected a task.");
    }

    /**
     * Prints a message indicating to user that list of tasks could not be saved to file. This is likely due to an
     * incorrect file path.
     */
    public void unableToSaveErrorMessage() {
        printResponse("Sorry, memory cannot be saved!");
    }

    /**
     * Prints a message indicating to user that commandline input is invalid.
     */
    public void unreadableCommandErrorMessage() {
        printResponse("I'm sorry, but I don't understand what that means! Try re-typing your instruction!");
    }

    /**
     * Prints a message indicating to user that list of tasks could not be loaded from file. This is likely due to an
     * incorrect file path.
     */
    public void loadingErrorMessage() {
        System.out.println("Sorry! I was unable to load your task list from memory!");
    }

    /**
     * Prints a message indicating to user that commandline input is incomplete.
     */
    public void incompleteCommandErrorMessage() {
        printResponse("OOPS!!! The description of this task is incomplete.");
    }

    /**
     * Prints a message indicating to user that date entered as commandline input is not in correct format.
     */
    public void invalidTiming() {
        printResponse("OOPS!!! You have key in an invalid date. (Format Example: 14 Oct 2023 23:00");
    }

    /**
     * Prints the given message, with a line above and below.
     */
    private static void printResponse(String response) {
        System.out.println(STR);
        System.out.println(response);
        System.out.println(STR);
    }

}
