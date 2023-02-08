package duke.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import duke.exception.DukeException;
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

    public void printWelcome() {
        System.out.println("           Hello! I am\n" + logo);
        System.out.println("    What can I do for you?");
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

    public String readCommand() throws DukeException {
        String command;

        try {
            command = br.readLine().trim();
        } catch (IOException e) {
            command = "";
        }

        if (command.isEmpty()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but input cannot be empty");
        } else {
            return command;
        }
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

    public String printList(TaskList taskList) {

        try {
            if (taskList.isEmpty()) {
                return "You have nothing in your tasklist";
            }

            String output = "Here are the tasks in your list: \n";
            output += taskList.printList();

            return output;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("hi");
        }
        return "";
    }

    public String printNoTaskWithKeywordFound(List<String> keyword) {
        return String.format("Sorry. No tasks were found to contain '%s' keyword.", keyword);
    }

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

    public String printAddedTask(Task task, TaskList taskList) {
        String output = "Got it. I've added this task: \n" + task + "\n";
        output += "Now you have " + taskList.size() + " tasks in the list.";

        return output;
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }
}
