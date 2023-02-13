package duke;

import java.time.LocalDateTime;

import duke.tasks.Task;

/**
 * Ui that interacts with the user and displays messages on the screen.
 *
 * @author Cheam Jia Wei
 */
public class Ui {
    /**
     * Message to greet the user.
     */
    public String greet() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Lists out the shows the user the tasks in the TaskList.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public String list(TaskList taskList) {
        return taskList.list();
    }

    /**
     * Displays the task that was marked.
     *
     * @param changed The task that was marked.
     */
    public String mark(Task changed) {
        return "Nice! I've marked this task as done:\n"
                + changed + "\n";
    }

    /**
     * Displays the task that was unmarked.
     *
     * @param changed The task that was unmarked.
     */
    public String unmark(Task changed) {
        return "Okay. I've unmarked the following task:\n"
                + changed + "\n";
    }

    /**
     * Displays the task that was added and the number of tasks currently
     * in the TaskList after addition.
     *
     * @param added The task that was added.
     * @param size Number of tasks in the TaskList.
     */
    public String taskAdded(Task added, int size) {
        return "Got it. I've added this task:\n" + added + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Displays the task that was deleted from the TaskList and the number
     * of tasks remaining in the TaskList
     *
     * @param removed The task that was deleted.
     * @param size Number of tasks remaining in the TaskList.
     */
    public String delete(Task removed, int size) {
        return "Noted. I've removed this task:\n" + removed + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Displays the tasks that are occurring on the specified date.
     *
     * @param input The string input by the user that has the date specified.
     * @param taskList The TaskList containing all the tasks and dates
     */
    public String through(String input, TaskList taskList) {
        LocalDateTime date = LocalDateTime.parse(input);
        return taskList.through(date);
    }

    /**
     * Displays the tasks that contain the specified keyword.
     *
     * @param input The keyword to find in the name of the tasks.
     * @param taskList The TaskList containing all the tasks.
     */
    public String find(String input, TaskList taskList) {
        return taskList.find(input);
    }
}
