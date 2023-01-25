package duke.ui;

import java.util.Scanner;

import duke.task.Task;

/**
 * User interface to scan inputs and print to console.
 */
public class Ui {

    /** Scanner to scan input from user */
    private Scanner scanner;

    /**
     * Constructs a new user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads one line of command from the user.
     *
     * @return Command from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes and cleans up resources used by the user interface.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Greets the user.
     */
    public void printGreeting() {
        System.out.println("Hello!");
    }

    /**
     * Bids farewell to the user.
     */
    public void printFareWell() {
        System.out.println("Good bye!");
    }

    /**
     * Prompts user to start giving commands.
     */
    public void printPrompt() {
        System.out.println("Awaiting commands...");
    }

    /**
     * Informs user that the command given is invalid.
     *
     * @param message Message to the user.
     */
    public void printBadCommandMessage(String message) {
        System.out.println(message);
    }

    /**
     * Informs user that a given task is successfully added to the task list.
     *
     * @param task Added task.
     */
    public void printAddTaskSuccessMessage(Task task) {
        System.out.println("Added task: " + task);
    }

    /**
     * Informs user that a given task is successfully marked as read or unread.
     *
     * @param task Marked task.
     */
    public void printMarkTaskSuccessMessage(Task task) {
        System.out.println("Marked task: " + task + " as " + (task.getIsDone() ? "" : "not ") + "done");
    }

    /**
     * Informs user that a given task is successfully deleted from the task list.
     *
     * @param task Deleted task.
     */
    public void printDeleteTaskSuccessMessage(Task task) {
        System.out.println("Deleted task: " + task);
    }

    /**
     * Informs user that an error occurred when loading the task list from disk.
     */
    public void printStorageLoadFailure() {
        System.out.println("Task list not found on disk, creating empty task list");
    }

    /**
     * Informs user that an error occurred when attempting to save the task list on disk.
     */
    public void printStorageSaveFailure() {
        System.out.println("Error saving tasks to disk!");
    }
}
