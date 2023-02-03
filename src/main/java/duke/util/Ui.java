package duke.util;

import duke.task.Task;

/**
 * The Ui class deals with interactions with the user.
 * @author Junyi
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
        String logo = "______     ______     __     __    \n"
                + "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n"
                + "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n"
                + " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n"
                + "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";

        return logo
                + "How shall I assist you today?";
    }

    /**
     * Displays a message and the task created.
     *
     * @param task The created Task.
     */
    public String showTaskCreatedMessage(Task task) {
        assert task != null : "Added task cannot be null!";
        return "Hey new task added!\n"
                + task;
    }

    /**
     * Displays a message that a task has been deleted.
     */
    public String showTaskDeletedMessage() {
        return "Task deleted. Are you skipping on work again?";
    }
}
