package duke.ui;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showDeleteMessage(Task task, String tasksLen) {
        System.out.println("Noted. I've removed this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list.");

    }
    public void showAddTaskMsg(Task task, String tasksLen) {
        System.out.println("Got it. I've added this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list.");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMarkedMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }


    public void showUnmarkedMsg(Task task) {
        System.out.println("Ok, I've marked this as not done yet:\n" + task);
    }


    public void showLoadingError() {
        System.out.println("No existing tasklist!");
    }

    public void showLine() {
        System.out.println("\n---------------------------------\n");
    }
}
