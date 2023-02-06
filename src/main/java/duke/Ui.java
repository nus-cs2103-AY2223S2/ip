package duke;

import duke.tasks.Task;

/**
 * The class for the UI of the program.
 */
public class Ui {

    /**
     * Prints an error message if loading tasks from text file fails.
     */
    public String showLoadingError() {
        return "Unable to load tasks from storage";
    }

    /**
     * Prints the welcome message on start.
     */
    public String showWelcomeMessage() {
        return "Hello I'm chopper\n"
                + "My commands are the following:\n"
                + "  1. todo <description>\n"
                + "  2. deadline <description> /by <yyyy-MM-dd\n      HHmm(optional)>\n"
                + "  3. event <description> /from <yyyy-MM-dd\n      HHmm(optional)> "
                + "/to <yyyy-MM-dd\n      HHmm(optional)>\n"
                + "  4. delete <task number>\n"
                + "  5. mark <task number>\n"
                + "  6. unmark <task number>\n"
                + "  7. list\n"
                + "  8. find <keyword(s)>\n"
                + "  9. bye\n"
                + "What can I do for you?";
    }

    /**
     * Prints the bye message when user inputs bye.
     */
    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * The confirmation message after performing an action.
     *
     * @param action String of the action.
     * @param tasks TaskList of all tasks.
     * @param task The task in question.
     * @return String The message itself.
     */
    public String confirmationMessage(String action, TaskList tasks, Task task) {
        return "Got it. I've " + action + " this task:\n"
                + "  "
                + task
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Return empty description message.
     * @return String The message.
     */
    public String emptyDescriptionError() {
        return "OOPS!!! The description of a task cannot be empty.";
    }

    /**
     * Return wrong deadline command format message.
     * @return String The message.
     */
    public String wrongDeadlineCommandFormat() {
        return "OOPS!!! Deadline must be in the format\n"
                + "deadline <description> /by <date>";
    }

    /**
     * Return wrong deadline date format message.
     * @return String The message.
     */
    public String wrongDeadlineDateFormat() {
        return "Deadline must have a date of the following format:\n"
                + "1. yyyy-MM-dd\n"
                + "2. yyyy-MM-dd HHmm";
    }

    /**
     * Return wrong event date format message.
     * @return String The message.
     */
    public String wrongEventDateFormat() {
        return "Event must have start and end dates of the following format:\n"
                + "1. yyyy-MM-dd\n"
                + "2. yyyy-MM-dd HHmm";
    }

    /**
     * Return wrong event command format message.
     * @return String The message.
     */
    public String wrongEventCommandFormat() {
        return "OOPS!!! Event must be in the format\n"
                + "event <description> /from <date> /to <date>";
    }

    /**
     * Return insufficient tasks message.
     * @return String The message.
     */
    public String insufficientTasksMessage() {
        return "There are insufficient tasks.";
    }

    /**
     * Return no tasks message.
     * @return String The message.
     */
    public String noTasksMessage() {
        return "There are no tasks.";
    }
}
