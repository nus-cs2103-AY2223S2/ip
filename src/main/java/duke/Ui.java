package duke;

/**
 * Handles interactions with the user
 */
public class Ui {
    private final String WELCOME_MESSAGE = "Hey Buddy, I'm Duke\nWhat can I do for you?";
    private final String GOODBYE_MESSAGE = "Goodbye friend. Hope to see you again soon!";

    /**
     * Returns the welcome message to the user upon starting
     *
     * @return the welcome message
     */
    public String welcomeUser() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns the goodbye message to the user upon termination
     *
     * @return the goodbye message
     */
    public String goodbyeUser() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns all the tasks inside a TaskList object
     *
     * @param taskList the TaskList object to be listed to the interface
     * @return the string representation of the TaskList object
     */
    public String listTasks(TaskList taskList) {
        return taskList.toString();
    }
}