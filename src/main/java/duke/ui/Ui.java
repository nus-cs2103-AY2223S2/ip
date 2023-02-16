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
        String taskStringToBePrinted = taskList.getSize() > 1 ? "tasks" : "task";
        return String.format("Got it. I've added this task:\n\n%s\n\nNow you have %d %s in the list.",
                task.toString(), taskList.getSize(), taskStringToBePrinted);
    }

    public static String getDeleteOutput(String taskString, TaskList taskList) {
        String taskStringToBePrinted = taskList.getSize() > 1 ? "tasks" : "task";
        return String.format("Noted. I've removed:\n%s\nNow you have %d %s in the list.",
                taskString, taskList.getSize(), taskStringToBePrinted);
    }

    public static String getMarkOutput(String taskString) {
        return taskString.equals("")
                ? "I marked nothing...\n"
                : String.format("Nice! I've marked:\n%s", taskString);
    }

    public static String getUnmarkOutput(String taskString) {
        return taskString.equals("")
                ? "I unmarked nothing...\n"
                : String.format("OK, I've unmarked:\n%s", taskString);
    }

    public static String getListOutput(TaskList taskList) throws DukeException {
        String taskStringToBePrinted = taskList.getSize() > 1 ? "tasks" : "task";
        if (taskList.getSize() > 0) {
            String str = String.format("Here are the %s in your list:\n", taskStringToBePrinted);
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
        String taskStringToBePrinted = filteredTasks.size() > 1 ? "tasks" : "task";
        if (filteredTasks.size() > 0) {
            String str = String.format("Here are the matching %s in your list:\n", taskStringToBePrinted);
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
