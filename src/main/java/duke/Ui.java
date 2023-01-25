package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showGreeting() {
        showTextWithLines("Hello! I'm Duke.\nWhat can I do for you?");
    }

    public void showGoodbye() {
        showTextWithLines("Bye. Hope to see you again soon!");
    }

    public void showAddTaskMessage(Task task, TaskList tasks) {
        showTextWithLines("Got it. I've added this task:\n  " + task + "\n" + tasks.describeLength());
    }

    public void showDeleteTaskMessage(Task task, TaskList tasks) {
        showTextWithLines("Noted. I've removed this task:\n  " + task + "\n" + tasks.describeLength());
    }

    public void showMarkTaskMessage(Task task) {
        showTextWithLines("Nice! I've marked this task as done:\n  " + task);
    }

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

    public void showError(String errorMessage) {
        showTextWithLines("Something went wrong:\n" + errorMessage);
    }

    public void showLoadingError() {
        showTextWithLines("Something went wrong while loading Duke.");
    }

    public void showTextWithLines(String text) {
        showLineBreak();
        System.out.println(text);
        showLineBreak();
        System.out.println();
    }

    public void showLineBreak() {
        System.out.println("_________________________________________________________________");
    }
}
