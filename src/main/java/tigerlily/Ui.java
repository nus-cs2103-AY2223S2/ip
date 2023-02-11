package tigerlily;

import tigerlily.exceptions.DukeExceptions;

import tigerlily.tasks.Task;
import tigerlily.tasks.TaskList;

public class Ui {
    private final String SEPARATOR = "\u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾";

    /**
     * Displays Tigerlily To-Do's welcome message when user starts session.
     */
    public void showWelcome() {
        System.out.println("｡ﾟﾟ･｡･ﾟﾟ｡\n" + "。 welcome to tigerlily to-do\n" + "　ﾟ･｡･ﾟ\n"
                + "✎ . . . . add your tasks here");
    }

    /**
     * Displays Tigerlily To-Do's goodbye message when user ends session.
     */
    public void showGoodbye() {
        System.out.println("\n(\\\\ (\\\\ \n" + "(„• ֊ •„)\n" + "━━O━O━━━━━━━━━━━━━━━\n"
                + "bye, see you again soon!\n" + "━━━━━━━━━━━━━━━━━━━━\n");
    }

    /**
     * Displays the given message.
     *
     * @param message the String to be displayed
     */
    public void showMessage(String message) {
        System.out.println("\n" + message + SEPARATOR);
    }

    /**
     * Displays the confirmation message when a Task has been successfully added to the TaskList.
     *
     * @param task the Task which has been added successfully
     * @param taskList the TaskList which the Task has been added to
     */
    public void showAddedMessage(Task task, TaskList taskList) {
        System.out.println("\nokay perf, your task: " + task.toString() + " has been added to your list\n"
                + "there are now " + taskList.getSize() + " task(s) in your list\n" + SEPARATOR);
    }

    /**
     * Displays the error message when a DukeException has been encountered.
     */
    public void showError(DukeExceptions error) {
        System.out.println("\n" + error + SEPARATOR);
    }
}