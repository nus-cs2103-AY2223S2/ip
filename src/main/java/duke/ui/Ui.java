package duke.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Provides the output string for the user's GUI.
 */
public class Ui {
    public static final String SEPARATOR = "------------------------------------------\n";

    public static String getWelcome() {
        return "Hello!\nI'm the one and only FAKE DUKE!\nWhat can I do for you?\n";
    }

    public static String getAddOutput(Task task, TaskList taskList) {
        return String.format("Got it. I've added this task:\n\n%s\n\nNow you have %d tasks in the list.",
                task.toString(), taskList.getSize());
    }

    public static String getDeleteOutput(String taskString, TaskList taskList) {
        return String.format("Noted. I've removed this task:\n\n%s\nNow you have %d tasks in the list.",
                taskString, taskList.getSize());
    }

    public static String getMarkOutput(String taskString) {
        return taskString.equals("")
                ? "I marked nothing...\n"
                : String.format("Nice! I've marked this task as done:\n%s", taskString);
    }

    public static String getUnmarkOutput(String taskString) {
        return taskString.equals("")
                ? "I unmarked nothing...\n"
                : String.format("OK, I've marked this task as not done yet:\n%s", taskString);
    }

    public static String getListOutput(TaskList taskList) throws DukeException {
        if (taskList.getSize() > 0) {
            String str = "Here are the tasks in your list:\n";
            for (int i = 1; i <= taskList.getSize(); i++) {
                str = String.format("%s%s%d. %s\n", str, SEPARATOR, i, taskList.getTask(i - 1).toString());
            }
            return str;
        } else {
            return "Your list is empty.";
        }
    }

    public static String getExitOutput() {
        return "Hope I have been useful to you.\nSee you again soon. Bye!~";
    }

    public static String getFindOrSortOutput(ArrayList<Task> filteredTasks) {
        if (filteredTasks.size() > 0) {
            String str = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= filteredTasks.size(); i++) {
                str = String.format("%s%s%d. %s\n", str, SEPARATOR, i, filteredTasks.get(i - 1));
            }
            return str;
        } else {
            return "Fake Duke can't find any matching tasks to sort :/ ";
        }
    }

    public static String getStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH:mma");
        return dateTime.format(formatter);
    }
}
