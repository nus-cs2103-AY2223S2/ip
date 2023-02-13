package duke;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * The class for the UI of the program.
 */
public class Ui {
    public static String showLoadingError() {
        return "Unable to load tasks from storage";
    }

    /**
     * Returns the welcome message on start.
     *
     * @return The String message.
     */
    public static String showWelcomeMessage() {
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
                + "  9. finddate <date>\n"
                + "  10. update <index> /<deadline or description> <new changes>\n"
                + "  11. bye\n"
                + "What can I do for you?";
    }

    public static String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Return tasks in the list.
     *
     * @param tasks The tasks.
     * @return String The message.
     */
    public static String printTasks(ArrayList<Task> tasks) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            result += "    "
                    + num
                    + ". "
                    + tasks.get(i)
                    + "\n";
        }
        return result;
    }

    /**
     * Mark task confirmation message.
     * @param task The task to be marked.
     * @return String The message.
     */
    public static String markTaskConfirmation(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Unmark task confirmation message.
     * @param task The task to be unmarked.
     * @return String The message.
     */
    public static String unmarkTaskConfirmation(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns the confirmation message after performing an action.
     *
     * @param action String of the action.
     * @param tasks  TaskList of all tasks.
     * @param task   The task in question.
     * @return String The message itself.
     */
    public static String confirmationMessage(String action, TaskList tasks, Task task) {
        String result = "Got it. I've " + action + " this task:\n"
                + "  "
                + task;
        if (!action.equals("updated")) {
            result += "\nNow you have "
                    + tasks.size()
                    + " tasks in the list.";
        }
        return result;
    }
    public static String emptyDescriptionError() {
        return "The description of a task cannot be empty.";
    }

    /**
     * Return wrong deadline command format message.
     *
     * @return String The message.
     */
    public static String wrongDeadlineCommandFormat() {
        return "Deadline must be in the format\n"
                + "deadline <description> /by <date>";
    }

    /**
     * Return wrong deadline date format message.
     *
     * @return String The message.
     */
    public static String wrongDeadlineDateFormat() {
        return "Deadline must have a date of the following format:\n"
                + "1. yyyy-MM-dd\n"
                + "2. yyyy-MM-dd HHmm";
    }
    /**
     * Return wrong event command format message.
     *
     * @return String The message.
     */
    public static String wrongEventCommandFormat() {
        return "Event must be in the format\n"
                + "event <description> /from <date> /to <date>";
    }

    /**
     * Return wrong event date format message.
     *
     * @return String The message.
     */
    public static String wrongEventDateFormat() {
        return "Event must have start and end dates of the following format:\n"
                + "1. yyyy-MM-dd\n"
                + "2. yyyy-MM-dd HHmm";
    }
    /**
     * Return wrong update command format message.
     *
     * @return String The message.
     */
    public static String wrongUpdateFormat() {
        return "Update command must be in the format\nupdate <index> /<deadline or description> <new changes>";
    }

    public static String wrongFindCommandFormat() {
        return "The find command is in the wrong format.";
    }

    public static String wrongFindDateCommand() {
        return "The finddate command is in the wrong format.";
    }

    public static String insufficientTasksMessage() {
        return "There are insufficient tasks.";
    }
    public static String noTasksMessage() {
        return "There are no tasks.";
    }


    public static String datePassed() {
        return "Date has passed.";
    }

    public static String startDatelaterThanEnd() {
        return "End date is earlier than start date.";
    }
    public static String unrecognisedCommand() {
        return "Unrecognised command. Try again.";
    }

    public static String missingIndex() {
        return "Command must be followed by an integer.";
    }
}
