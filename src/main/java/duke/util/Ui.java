package duke.util;

import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class deals with interactions with the user.
 * @author Junyi
 */
public class Ui {

    /* Responsible for reading user input */
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
        String logo = "______     ______     __     __    \n"
                + "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n"
                + "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n"
                + " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n"
                + "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";
        System.out.println("Hello, I am \n" + logo);
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
