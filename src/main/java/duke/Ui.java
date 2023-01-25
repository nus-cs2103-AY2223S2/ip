package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
public class Ui {
    private final Scanner sc;
    private static final String DIVIDER = "==========================";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void showWelcome() {
        showLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public static void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showFatalError(String errorMessage) {
        System.out.println(errorMessage);
        System.exit(1);
    }

    public static void showLine() {
        System.out.println(DIVIDER);
    }

    public static void showTaskAdded(Task task, int counter) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + counter + " task in the list");
    }

    public static void showTaskMarked(Task task, boolean isMarked) {
        if (isMarked) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("    " + task);
        }
    }

    public static void showTaskDeleted(Task task, int counter) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public static void showTaskList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int label = i + 1;
            System.out.println("    " + label + ". " + tasks.get(i).toString());
        }
    }

    public static void showInvalidCommand() {
        System.out.println("The command entered is invalid.");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
