package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interaction with the user and prints information about the program.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an input command from the user.
     *
     * @return The input command by the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a greeting message.
     */
    public void showGreeting() {
        showTextWithLines("Hello! I'm Duke.\nWhat can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        showTextWithLines("Bye. Hope to see you again soon!");
    }

    /**
     * Prints information about the task list after adding a task.
     *
     * @param task  Task that was added.
     * @param tasks Task list that task was added to.
     */
    public void showAddTaskMessage(Task task, TaskList tasks) {
        showTextWithLines("Got it. I've added this task:\n  " + task + "\n" + tasks.describeLength());
    }

    /**
     * Prints information about the task list after deleting a task.
     *
     * @param task  Task that was deleted.
     * @param tasks Task list that task was deleted from.
     */
    public void showDeleteTaskMessage(Task task, TaskList tasks) {
        showTextWithLines("Noted. I've removed this task:\n  " + task + "\n" + tasks.describeLength());
    }

    /**
     * Prints information about the task list after marking a task as complete.
     *
     * @param task Task that was marked as complete.
     */
    public void showMarkTaskMessage(Task task) {
        showTextWithLines("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Prints information about the task list after marking a task as uncomplete.
     *
     * @param task Task that was marked as uncomplete.
     */
    public void showUnmarkTaskMessage(Task task) {
        showTextWithLines("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * Prints information about the tasks found with the search string.
     *
     * @param tasks List of tasks that were found by the search string.
     */
    public void showFindTaskMessage(ArrayList<Task> tasks) {
        StringBuilder tasksStr = new StringBuilder();
        tasksStr.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksStr.append(i + 1).append('.').append(tasks.get(i).toString()).append('\n');
        }
        if (tasksStr.length() > 0) {
            tasksStr.deleteCharAt(tasksStr.length() - 1);
        }
        showTextWithLines(tasksStr.toString());
    }

    /**
     * Prints information about an error message.
     *
     * @param errorMessage Error message describing the error.
     */
    public void showError(String errorMessage) {
        showTextWithLines("Something went wrong:\n" + errorMessage);
    }

    /**
     * Prints an error message for unexpected errors.
     */
    public void showLoadingError() {
        showTextWithLines("Something went wrong while loading Duke.");
    }

    /**
     * Prints text in a pretty format.
     *
     * @param text Text to be printed.
     */
    public void showTextWithLines(String text) {
        showLineBreak();
        System.out.println(text);
        showLineBreak();
        System.out.println();
    }

    /**
     * Prints a line break using underscores.
     */
    public void showLineBreak() {
        System.out.println("_________________________________________________________________");
    }
}
