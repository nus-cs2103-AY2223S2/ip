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
        return "I hate to tell you, but you got the datetime syntax is wrong!";
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
            return "It doesn't hurt to have more tasks. Mark my words.";
        }

        String output = "Our country is going to be in serious trouble if you don't start doing something: \n";
        output += taskList.toList();

        return output;
    }

    public String printSortedList(TaskList taskList) {

        TaskList sortedList = taskList.sortList();
        String output = "I'm tired of seeing all talk and no action: \n";
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
        String output = "What you’re finding and what you’re seeing is not what’s happening: \n";

        for (int i = 0; i < taskList.size(); i++) {
            Task toDo = taskList.get(i);
            output += (i + 1) + "." + toDo + "\n";
        }

        return output;
    }

    public String printMarkedTask(Task task) {
        return "C'mon the great wall isn't gonna be building itself by marking: \n" + task;
    }

    public String printUnmarkedTask(Task task) {
        return "Unmarking tasks? Now we're going to lose the next election: \n" + task;
    }

    public String printDeletedTask(Task task) {
        return "I will have greater respect for you if you stop deleting tasks and actually start doing them: \n" + task;
    }

    /**
     * Print when added task successfully
     *
     * @param task     Task to be added
     * @param taskList List of existing tasks
     * @return String output to be seen by users
     */
    public String printAddedTask(Task task, TaskList taskList) {
        String output = "You are not measured by how much tasks you add but how many you actually accomplish: \n" + task + "\n";
        output += "Now you have " + taskList.size() + " tasks in the list.";

        return output;
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printAvailability(LocalDateTime date, String input) {
        return "You are free for " + input + " from " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
    }
}
