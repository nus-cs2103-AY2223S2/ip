package tigerlily;

import tigerlily.exceptions.DukeExceptions;

import tigerlily.tasks.Task;
import tigerlily.tasks.TaskList;

public class Ui {
    /**
     * Displays Tigerlily To-Do's welcome message when user starts session.
     */
    public String showWelcome() {
        return "welcome to tigerlily to-do,\nadd your tasks here!";
    }

    /**
     * Displays Tigerlily To-Do's goodbye message when user ends session.
     */
    public String showGoodbye() {
        return "bye, see you again soon!";
    }

    /**
     * Displays the given message.
     *
     * @param message the String to be displayed
     */
    public String showMessage(String message) {
        return "\n" + message;
    }

    /**
     * Displays the confirmation message when a Task has been successfully added to the TaskList.
     *
     * @param task the Task which has been added successfully
     * @param taskList the TaskList which the Task has been added to
     */
    public String showAddedMessage(Task task, TaskList taskList) {
        return "\nokay perf, your task: " + task.toString() + " has been added to your list\n" +
                "there are now " + taskList.getSize() + " task(s) in your list\n";
    }

    /**
     * Displays the error message when a DukeException has been encountered.
     */
    public String showError(DukeExceptions error) {
        return "\n" + error;
    }
}