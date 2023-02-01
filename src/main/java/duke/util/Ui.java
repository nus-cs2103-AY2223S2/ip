package duke.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void showError(String message) {
        System.out.println("    " + message);
    }

    public void showInvalidTimeError() {
        System.out.println("    ☹ OOPS!!! Invalid datetime. Syntax: YYYY-MM-DD");
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

    public void printTaskSaved() {
        System.out.println("    Tasks saved successfully. ");
    }

    public void printTaskExistence() {
        printLine();
        System.out.println("    Here are your existing tasks: ");
    }

    public void printTask(Task task) {
        System.out.println("    " + task);
    }

    public void printList(TaskList taskList) {
        System.out.println("    Here are the tasks in your list: ");

        for (int i = 0; i < taskList.size(); i++) {
            Task toDo = taskList.get(i);
            System.out.println("    " + (i + 1) + "." + toDo);
        }
    }

    public void printNoTaskWithKeywordFound(String keyword) {
        System.out.printf("    Sorry. No tasks were found to contain '%s' keyword.", keyword);
        System.out.println();
    }

    public void printFoundList(TaskList taskList) {
        System.out.println("    Here are the matching tasks in your list: ");

        for (int i = 0; i < taskList.size(); i++) {
            Task toDo = taskList.get(i);
            System.out.println("    " + (i + 1) + "." + toDo);
        }
    }

    public void printMarkedTask(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    public void printUnmarkedTask(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }

    public void printDeletedTask(Task task) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
    }

    public void printAddedTask(Task task, TaskList taskList) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
