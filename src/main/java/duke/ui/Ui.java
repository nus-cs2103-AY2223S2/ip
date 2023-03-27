package duke.ui;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The user interface class.
 * Handles all user input and output.
 */
public class Ui {
    /**
     * Prints the welcome message.
     * @return The string to be printed.
     */
    public String printWelcomeMessage() {
        return "\t____________________________________________________________\n"
                + "\tHello! I'm NoDuKo\n"
                + "\tWhat can I not do for you?\n"
                + "\t____________________________________________________________";
    }

    /**
     * Prints the bye message.
     * @return The string to be printed.
     */
    public String printByeMessage() {
        return "\t____________________________________________________________\n"
                + "\tBye. Hope to not see you again soon!\n"
                + "\t____________________________________________________________";
    }

    /**
     * Prints the task added message.
     * @param task      The task that was added.
     * @param taskCount The number of tasks in the task list after addition.
     * @return The string to be printed.
     */
    public String printTaskAdded(Task task, int taskCount) {
        return "\tGot it. I've added this task:\n"
                + "\t\t" + task + "\n"
                + "\tNow you have " + taskCount + " tasks in the list.\n \tThat's way too many.";
    }

    /**
     * Prints the task marked message.
     * @param task The task that was marked.
     * @return The string to be printed.
     */
    public String printTaskMarked(Task task) {
        return "\tNice! I've marked this task as done:\n"
                + "\t\t" + task;
    }

    /**
     * Prints the sorted tasks according to whether they are done or not and in
     * date order.
     * @param tasks The list of tasks to be printed.
     * @return The string to be printed.
     */
    public String printSortedTasks(ArrayList<Task> tasks) {
        String output = "\tHere are the tasks in your list, ohno:";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            output += "\n\t\t" + (i + 1) + "." + t;
        }
        return output;
    }

    /**
     * Prints the task unmarked message.
     * @param task The task that was unmarked.
     * @return The string to be printed.
     */
    public String printTaskUnmarked(Task task) {
        return "\tGood luck! I've marked this task as not done:\n"
                + "\t\t" + task;
    }

    /**
     * Prints the task deleted message.
     * @param task      The task that was deleted.
     * @param taskCount The number of tasks in the task list after deletion.
     * @return The string to be printed.
     */
    public String printTaskDeleted(Task task, int taskCount) {
        return "\tWhy? I've removed this task:\n"
                + "\t\t" + task + "\n"
                + "\tNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Prints the task list.
     * @param taskList The task list to be printed.
     * @return The string to be printed.
     */
    public String printTaskList(TaskList taskList) {
        String output = "\tHere are the tasks in your list:";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            output += "\n\t\t" + (i + 1) + "." + t;
        }
        return output;
    }

    /**
     * Prints the found tasks.
     * @param foundTasks The list of found tasks.
     * @return The string to be printed.
     */
    public String printFoundTasks(ArrayList<Task> foundTasks) {
        String output = "\tHere are the matching tasks in your list:";
        for (int i = 0; i < foundTasks.size(); i++) {
            Task t = foundTasks.get(i);
            output += "\n\t\t" + (i + 1) + "." + t;
        }
        return output;
    }

    /**
     * Prints the error message for a DukeException.
     * @param e The DukeException to be printed.
     * @return The string to be printed.
     */
    public String printError(DukeException e) {
        return "\t____________________________________________________________\n"
                + "\t" + e.getMessage() + "\n"
                + "\t____________________________________________________________";
    }

    /**
     * Prints the error message for an unknown command.
     * @return The string to be printed.
     */
    public String printErrorActionMessage() {
        return "\t____________________________________________________________\n"
                + "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "\t____________________________________________________________";
    }
}
