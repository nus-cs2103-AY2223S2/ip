package duke.util;

import duke.task.Task;

/**
 * The Ui class deals with interactions with the user.
 * @author Junyi
 */
public class Ui {

    private static Ui instance;

    private Ui() {}

    /**
     * Get the Ui instance.
     * @return An instance of Ui.
     */
    public static Ui getUi() {
        if (Ui.instance == null) {
            Ui.instance = new Ui();
        }

        return instance;
    }

    /**
     * Returns the error message that data loading failed.
     */
    public String showLoadingError() {
        return "Failed to load data file!";
    }

    /**
     * Returns the message that the task data has been stored successfully.
     */
    public String showSavedDataMessage() {
        return "Your tasks is now safely stored.";
    }

    /**
     * Returns the welcome message.
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
     * Returns the message and the task created.
     *
     * @param task The created Task.
     */
    public String showTaskCreatedMessage(Task task) {
        assert task != null : "Added task cannot be null!";
        return "Hey new task added!\n"
                + task;
    }

    /**
     * Returns the message that a task has been deleted.
     */
    public String showTaskDeletedMessage() {
        return "Task deleted. Are you skipping on work again?";
    }
}
