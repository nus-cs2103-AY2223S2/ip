package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * A UI class handling output to user.
 */
public class UI {
    /**
     * Returns a formatted success message.
     * @param message message to be printed.
     * @param task task to be included.
     * @return formatted message to be printed.
     */
    public String formatSuccessMessage(String message, Task task) {
        return message + "\n" + task.toString();
    }

    /**
     * Returns a formatted task message.
     * @param message message to be printed.
     * @param task task to be included.
     * @param numOfTasks number of task to be included
     * @return formatted task message to be printed.
     */
    public String formatTaskMessage(String message, Task task, int numOfTasks) {
        return message + "\n"
                + "      " + task.toString() + "\n"
                + "Now you have " + numOfTasks + " task(s) in the list.";
    }

    /**
     * Returns the result of a find command.
     * @param tasks task to be included.
     * @return formatted find result to be printed.
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
