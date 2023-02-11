package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * A UI class handling output to user.
 */
public class UI {
    /**
     * Returns a formatted success message.
     */
    public String formatSuccessMessage(String message, Task task) {
        return message + "\n" + task.toString();
    }

    /**
     * Returns a formatted task message.
     */
    public String formatTaskMessage(String message, Task task, int numOfTasks) {
        return message + "\n"
                + "      " + task.toString() + "\n"
                + "Now you have " + numOfTasks + " task(s) in the list.";
    }

    /**
     * Prints the result of a find command.
     */
    public String formatFindResult(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No corresponding task found!";
        } else {
            String message = "Here are the matching tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                message += "\n" + (i + 1) + ". " + tasks.get(i).toString();
            }
            return message;
        }
    }
}
