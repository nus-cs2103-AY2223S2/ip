package duke;

import duke.exception.DukeException;

import duke.task.Task;

/** Class that handles user interface */
public class Ui {

    /**
     * Prints greeting text.
     */
    public static String showGreeting() {
        String greeting = "Hello! I'm Colette.\n"
                + "What can I do for you?";
        return greeting;
    }

    /**
     * Prints error message.
     *
     * @param e Error.
     */
    public String showErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints user's tasks.
     *
     * @param tasks User's task list.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There are currently no tasks in your list.";
        } else {
            return "Here are the tasks in your list:\n" + tasks;
        }
    }

    /**
     * Prints text for adding task to task list.
     *
     * @param task Task to be added.
     * @param tasks Task list.
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints text for marking task.
     *
     * @param task Task that has been marked.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints text for unmarking task.
     *
     * @param task Task that has been unmarked.
     */
    public String showUnmarkTask(Task task) {
        return "Got it. I've marked this task as not done:\n" + task;
    }

    /**
     * Prints text for deleting task.
     *
     * @param task Deleted task.
     * @param tasks Task list.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints goodbye text.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints matching tasks.
     * @param tasks TaskList containing matching tasks.
     */
    public String showFind(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There are no matching tasks.";
        } else {
            return "Here are the matching tasks in your list:\n" + tasks;
        }
    }

}
