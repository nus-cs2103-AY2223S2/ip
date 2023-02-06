package duke;

import java.util.ArrayList;

import tasks.Task;

/**
 * Handles interaction with the user and prints information about the program.
 */
public class Ui {
    /**
     * The message to be shown before quitting the application.
     *
     * @return The goodbye message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns human readable information about the task list after adding a task.
     *
     * @param task  Task that was added.
     * @param tasks Task list that task was added to.
     * @return The relevant information about the task list.
     */
    public String getAddTaskMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  " + task + "\n" + tasks.describeLength();
    }

    /**
     * Returns humanreadable information about the task list after deleting a task.
     *
     * @param task  Task that was deleted.
     * @param tasks Task list that task was deleted from.
     * @return The relevant information about the task list.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n  " + task + "\n" + tasks.describeLength();
    }

    /**
     * Returns humanreadable information about the task list after marking a task as complete.
     *
     * @param task Task that was marked as complete.
     * @return The relevant information about the task list.
     */
    public String getMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns humanreadable information about the task list after marking a task as uncomplete.
     *
     * @param task Task that was marked as uncomplete.
     * @return The relevant information about the task list.
     */
    public String getUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns humanreadable information about the tasks found with the search string.
     *
     * @param tasks List of tasks that were found by the search string.
     * @return The relevant information about the tasks.
     */
    public String getFindTaskMessage(ArrayList<Task> tasks) {
        StringBuilder tasksStr = new StringBuilder();
        tasksStr.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksStr.append(i + 1).append('.').append(tasks.get(i).toString()).append('\n');
        }
        if (tasksStr.length() > 0) {
            tasksStr.deleteCharAt(tasksStr.length() - 1);
        }
        return tasksStr.toString();
    }

    /**
     * Returns humanreadable information about an error message.
     *
     * @param errorMessage Error message describing the error.
     * @return Human readable information about the error message.
     */
    public String getErrorMessage(String errorMessage) {
        return "Something went wrong:\n" + errorMessage;
    }

    /**
     * An error message for unexpected errors.
     *
     * @return The error message.
     */
    public String getLoadingError() {
        return "Something went wrong while loading Duke.";
    }
}
