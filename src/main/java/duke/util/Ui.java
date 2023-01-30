package duke.util;

import duke.Duke;
import duke.task.Task;

import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {

    /**
     * Displays an error message that data loading failed.
     */
    public String showLoadingError() {
        return "Failed to load data file!";
    }

    /**
     * Displays a message that the task data has been stored successfully.
     */
    public String showSavedDataMessage() {
        return "Your tasks is now safely stored.";
    }

    /**
     * Displays a welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello, I am \n" + Duke.LOGO + "\n" +
                "How shall I assist you today?";
    }

    /**
     * Displays a message and the task created.
     *
     * @param task The created Task.
     */
    public String showTaskCreatedMessage(Task task) {
        return "Hey new task added!\n" +
                task;
    }

    /**
     * Displays a message that a task has been deleted.
     */
    public String showTaskDeletedMessage() {
        return "Task deleted. Are you skipping on work again?";
    }
}
