package duke.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui handles everything that is shown to user
 *
 * @author Guo-KeCheng
 */
public class Ui {
    private static final String line = "    ----------------------------------------";
    private static final String logo = "____    ____  __    __   __    __  \n"
            + "\\   \\  /   / |  |  |  | |  |  |  |\n"
            + " \\   \\/   /  |  |  |  | |  |  |  | \n"
            + "  \\_    _/   |  |  |  | |  |  |  | \n"
            + "    |  |     |  `--'  | |  `--'  | \n"
            + "    |__|      \\______/   \\______/  \n";
    private static BufferedReader br;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printLine() {
        System.out.println(line);
    }

    public String showError(String message) {
        return message;
    }

    public String showInvalidTimeError() {
        return "Invalid datetime. Syntax: YYYY-MM-DD";
    }

    public String printTaskSaved() {
        return "Tasks saved successfully.";
    }

    public void printTaskExistence() {
        System.out.println("Here are your existing tasks: ");
    }

    public String printTask(Task task) {
        return task.toString();
    }

    /**
     * Prints out the list of existing tasks
     *
     * @param taskList List of existing tasks
     * @return String representation of list of tasks
     */
    public String printList(TaskList taskList) {

        if (taskList.isEmpty()) {
            return "You have nothing in your tasklist";
        }

        String output = "Here are the tasks in your list: \n";
        output += taskList.toList();

        return output;
    }

    public String printSortedList(TaskList taskList) {

        TaskList sortedList = taskList.sortList();
        String output = "Here are the sorted tasks in your list: \n";
        output += sortedList.toList();

        return output;
    }

    public String printNoTaskWithKeywordFound(List<String> keyword) {
        return String.format("Sorry. No tasks were found to contain '%s' keyword.", keyword);
    }

    /**
     * Print out list of tasks that matches with keyword
     *
     * @param taskList TaskList to be compared against
     * @return String representation of list of matching tasks
     */
    public String printFoundList(TaskList taskList) {
        String output = "Here are the matching tasks in your list: \n";

        for (int i = 0; i < taskList.size(); i++) {
            Task toDo = taskList.get(i);
            output += (i + 1) + "." + toDo + "\n";
        }

        return output;
    }

    public String printMarkedTask(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String printUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task;
    }

    public String printDeletedTask(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Print when added task successfully
     *
     * @param task     Task to be added
     * @param taskList List of existing tasks
     * @return String output to be seen by users
     */
    public String printAddedTask(Task task, TaskList taskList) {
        String output = "Got it. I've added this task: \n" + task + "\n";
        output += "Now you have " + taskList.size() + " tasks in the list.";

        return output;
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printAvailability(LocalDateTime date, String input) {
        return "You are free for " + input + " from " + date.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy"));
    }
}
