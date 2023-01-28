package duke.gui;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/** Class that handles the text of the graphical user interface */
public class GuiText {

    /**
     * Generates greeting text.
     *
     * @return Greeting text.
     */
    public static String showGreeting() {
        String greeting = "Hello! I'm Colette.\n"
                + "What can I do for you?";
        return greeting;
    }

    /**
     * Generates error text from given exception.
     *
     * @param e Error.
     * @return Error text.
     */
    public String showErrorMessage(DukeException e) {
        return e.getMessage();
    }

    //public static String a bunch of erorr stuff...

    /**
     * Generates text response listing user's tasks.
     *
     * @param tasks User's task list.
     * @return Text response listing tasks.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There are currently no tasks in your list. Would you like to add some?";
        } else {
            return "Here are all the tasks in your list:\n"
                + tasks;
        }
    }

    /**
     * Generates text response for adding task to task list.
     *
     * @param task Task to be added.
     * @param tasks Task list.
     * @return Text response for adding task to task list.
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Got it! I've added this task:\n"
                + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Generates text response for marking task.
     *
     * @param task Task that has been marked.
     * @return Text response for marking task.
     */
    public String showMarkTask(Task task) {
        return "Yay! I've marked this task as done:\n" + task;
    }

    /**
     * Generates text response for unmarking task.
     *
     * @param task Task that has been unmarked.
     * @return Text response for unmarking task.
     */
    public String showUnmarkTask(Task task) {
        return "Okay! I've marked this task as not done:\n" + task;
    }

    /**
     * Generates text response for deleting task.
     *
     * @param task Deleted task.
     * @param tasks Task list.
     * @return Text response for deleting task.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Alright! I've removed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Generates goodbye text.
     *
     * @return Goodbye text.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates text listing matching tasks.
     *
     * @param tasks TaskList containing matching tasks.
     * @return Text listing matching tasks.
     */
    public String showFind(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There are no matching tasks.";
        } else {
            return "Here are all the matching tasks in your list:\n"
                    + tasks;
        }
    }

    public String showLoad(boolean isSuccessful) {
        return isSuccessful ? "Tasks successfully loaded from storage!" : "Loading from storage failed.";
    }

}
