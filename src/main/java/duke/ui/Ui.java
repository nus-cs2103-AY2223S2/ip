package duke.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String SEPARATOR = "------------------------------------------\n";

    /**
     * Prints out the greeting for Fake Duke.
     */
    public static String getWelcome() {
        return "Hello!\nI'm the one and only FAKE DUKE!\nWhat can I do for you?\n";
    }

    /**
     * Prints message when task is added into task list.
     *
     * @param task Task that is added into the task list.
     * @param taskList List of tasks.
     */
    public static String getAddOutput(Task task, TaskList taskList) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(), taskList.getSize());
    }

    /**
     * Prints message when task is deleted from the list of tasks.
     *
     * @param taskString Description of task that has been deleted from the list of tasks.
     * @param taskList List of tasks.
     */
    public static String getDeleteOutput(String taskString, TaskList taskList) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                taskString, taskList.getSize());
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param taskString Task in String that has been marked as done.
     */
    public static String getMarkOutput(String taskString) {
        return taskString.equals("")
                ? "I marked nothing...\n"
                : String.format("Nice! I've marked this task as done:\n%s", taskString);
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param taskString Task in String that has been unmarked as undone.
     */
    public static String getUnmarkOutput(String taskString) {
        return taskString.equals("")
                ? "I unmarked nothing...\n"
                : String.format("OK, I've marked this task as not done yet:\n%s", taskString);
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList List of tasks.
     * @throws DukeException Throws exception if unable to get task.
     */
    public static String getListOutput(TaskList taskList) throws DukeException {
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            str = String.format("%s%s%d. %s\n", str, SEPARATOR, i, taskList.getTask(i - 1).toString());
        }
        return str;
    }


    /**
     * Prints an exit message.
     */
    public static String getExitOutput() {
        return "Hope I have been useful to you.\nSee you again soon. Bye!~";
    }

    /**
     * Prints the subset list of tasks that matches the keyword provided by the user.
     *
     * @param foundTasks Subset list of tasks.
     */
    public static String getFindOutput(ArrayList<Task> foundTasks) {
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= foundTasks.size(); i++) {
            str = String.format("%s%d. %s\n", str, i, foundTasks.get(i - 1));
        }
        return str;
    }

    public static String getSortOutput(ArrayList<Task> filteredTasks) {
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= filteredTasks.size(); i++) {
            str = String.format("%s%d. %s\n", str, i, filteredTasks.get(i - 1));
        }
        return str;
    }

    /**
     * Returns datetime in String for printing.
     *
     * @param dateTime Datetime of Task.
     * @return String representation of datetime.
     */
    public static String getStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH:mma");
        return dateTime.format(formatter);
    }
}
