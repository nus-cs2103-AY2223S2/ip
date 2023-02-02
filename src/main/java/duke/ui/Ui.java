package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner SCANNER = new Scanner(System.in);

    public String readCommand(String command) throws DukeException {
        if (command.contains("~")) {
            throw new DukeException("Fake Duke doesn't like it when the input has the symbol ~.");
        }
        return command;
    }

    /**
     * Prints out the greeting for Fake Duke.
     */
    public String showWelcome() {
       return "Hello!~ I'm the one and only ✨ FAKE DUKE ✨\nWhat can I do for you?\n";
    }

    /**
     * Prints message when task is added into task list.
     *
     * @param task Task that is added into the task list.
     * @param taskList List of tasks.
     */
    public String getAddOutput(Task task, TaskList taskList) {
        return String.format("Got it. I've added this task:\n%s)\nNow you have %d tasks in the list.",
                task.toString(), taskList.getSize());
    }

    /**
     * Prints message when task is deleted from the list of tasks.
     *
     * @param taskString Description of task that has been deleted from the list of tasks.
     * @param taskList List of tasks.
     */
    public String getDeleteOutput(String taskString, TaskList taskList) {
        return String.format("Noted. I've removed this task:\n%sNow you have %d tasks in the list.",
                taskString, taskList.getSize());
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param taskString Task in String that has been marked as done.
     */
    public String getMarkOutput(String taskString) {
        return String.format("Nice! I've marked this task as done:\n%s", taskString);
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param taskString Task in String that has been unmarked as undone.
     */
    public String getUnmarkOutput(String taskString) {
        return String.format("OK, I've marked this task as not done yet:\n%s", taskString);
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList List of tasks.
     * @throws DukeException Throws exception if unable to get task.
     */
    public String getListOutput(TaskList taskList) throws DukeException {
        String str = String.format("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskList.getSize(); i++) {
            str = String.format("%s%d.%s\n", str, i, taskList.getTask(i - 1).toString());
        }
        return str;
    }


    /**
     * Prints an exit message.
     */
    public String getExitOutput() {
        return "Hope I have been useful to you.\nSee you again soon. Bye!~";
    }

    /**
     * Prints the subset list of tasks that matches the keyword provided by the user.
     *
     * @param foundTasks Subset list of tasks.
     */
    public String getFindOutput(ArrayList<Task> foundTasks) {
        String str = String.format("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= foundTasks.size(); i++) {
            str = String.format("%s%d.%s\n", str, i, foundTasks.get(i - 1));
        }
        return str;
    }

    /**
     * Returns datetime in String for printing.
     *
     * @param dateTime Datetime of Task.
     * @return String representation of datetime.
     */
    public String getStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH:mma");
        return dateTime.format(formatter);
    }
}
