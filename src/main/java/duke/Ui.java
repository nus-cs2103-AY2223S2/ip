package duke;

import tasks.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

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
