package duke.util;

import duke.Duke;
import duke.task.Task;

import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays and request user for a command.
     * @return The user command.
     */
    public String requestUserInput() {
        System.out.print("\n:> ");
        return scanner.nextLine();
    }

    /**
     * Displays an error message that data loading failed.
     */
    public void showLoadingError() {
        System.out.println("Failed to load data file!");
    }

    /**
     * Displays a message that the task data has been stored successfully.
     */
    public void showSavedDataMessage() {
        System.out.println("Your tasks is now safely stored.");
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello, I am \n" + Duke.LOGO);
        System.out.println("How shall I assist you today?");
    }

    /**
     * Displays a message and the task created.
     * @param task The created Task.
     */
    public void showTaskCreatedMessage(Task task) {
        System.out.println("Hey new task added!");
        System.out.println(task);
    }

    /**
     * Displays a message that a task has been deleted.
     */
    public void showTaskDeletedMessage() {
        System.out.println("Task deleted. Are you skipping on work again?");
    }

    /**
     * Closes this Ui
     */
    public void close() {
        scanner.close();
    }
}
