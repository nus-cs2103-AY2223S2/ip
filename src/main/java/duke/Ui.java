package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    /** Stores the message to respond to the user's input */
    private StringBuilder message = new StringBuilder();

    /**
     * Stores the welcome message in message attribute.
     */
    public void printWelcome() {
        message.append("Hello! I'm C-3PO, Human Cyborg Relations.\nWhat can I do for you?");
    }

    /**
     * Stores the goodbye message in message attribute.
     */
    public void printGoodBye() {
        message.append("Bye. Hope to see you again soon!");
    }

    /**
     * Stores the list of tasks in message attribute.
     *
     * @param listOfTasks Array list of tasks.
     */
    public void listTasks(ArrayList<Task> listOfTasks) {
        Task task;
        message.append("Here are the tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            message.append("\n" + i + "." + task);
        }
    }

    /**
     * Stores the list of matching tasks in message attribute.
     *
     * @param listOfTasks Array list of matching tasks.
     */
    public void printMatchingTasks(ArrayList<Task> listOfTasks) {
        Task task;
        message.append("Here are the matching tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            message.append("\n" + i + "." + task);
        }
    }

    /**
     * Stores the task that was saved in message attribute.
     *
     * @param task Task that was saved.
     * @param listOfTasks Array list of tasks.
     */
    public void printSaveTask(Task task, ArrayList<Task> listOfTasks) {
        message.append("Got it. I've added this task:");
        message.append("\n  " + task);
        message.append("\nNow you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Stores the task that was marked as done in message attribute.
     *
     * @param task Task that was marked.
     */
    public void printMarkTask(Task task) {
        message.append("Nice! I've marked this task as done:");
        message.append("\n  " + task);
    }

    /**
     * Stores the task that was marked as not done in message attribute.
     *
     * @param task Task that was unmarked.
     */
    public void printUnmarkTask(Task task) {
        message.append("OK, I've marked this task as not done yet:");
        message.append("\n  " + task);
    }

    /**
     * Stores the task that was deleted in message attribute.
     *
     * @param task Task that was deleted.
     * @param listOfTasks Array list of tasks.
     */
    public void printDeleteTask(Task task, ArrayList<Task> listOfTasks) {
        message.append("Noted. I've removed this task:");
        message.append("\n  " + task);
        message.append("\nNow you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Stores the error when the data could
     * not be loaded in message attribute.
     */
    public void showLoadingError() {
        message.append("Creating new file to store tasks due to a loading error ...");
        message.append("\nWARNING!!! Continuing with the program will reset the contents of ");
        message.append("\nthe original file (if it exists).");
        message.append("\nExit the program if you do not wish to continue (enter 'bye')");
    }

    /**
     * Store the error when file or folder
     * could not be created in message attribute.
     */
    public void showFileError() {
        message.append("Error occurred when creating the folder/file.");
    }

    /**
     * Stores error message for unknown command in message attribute.
     */
    public void showUnknownError() {
        message.append("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Stores error message for empty task description in message attribute.
     */
    public void showDescriptionError(String task) {
        message.append("OOPS!!! The description of " + task + " task cannot be empty.");
    }

    /**
     * Stores error message for out of bounds index in message attribute.
     */
    public void showIndexError(int index) {
        message.append("OOPS!!! The index " + index + " for the list of tasks is out of bounds.");
    }

    /**
     * Stores error message for incorrect date time format in message attribute.
     */
    public void showDateTimeError() {
        message.append("OOPS!!! Incorrect date time format. Use dd/mm/yyyy HHmm instead.");
    }

    public StringBuilder getMessage() {
        return message;
    }

    /**
     * Clears the message attribute.
     */
    public void clearMessage() {
        message.setLength(0);
    }
}
