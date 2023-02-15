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

    public static String showWelcome() {
        return "Hello I'm Duke\nWhat can I do for you?";
    }

    public static String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String showError(String errorMessage) {
        return (errorMessage);
    }

    public static String showFatalError(String errorMessage) {
        return (errorMessage);
    }

    public static String showLine() {
        return(DIVIDER);
    }

    public static String showTaskAdded(Task task, int counter) {
        return "Got it. I've added this task:\n" +
                "    " + task +
                "\nNow you have " + counter + " task in the list";
    }

    public static String showTaskMarked(Task task, boolean isMarked) {
        if (isMarked) {
            return "Nice! I've marked this task as done:\n" +
                    "    " + task;
        } else {
            return "OK, I've marked this task as not done yet:\n" +
                    "    " + task;
        }
    }

    public static String showTaskDeleted(Task task, int counter) {
        return "Got it. I've removed this task:\n" +
                "    " + task +
                "\nNow you have " + counter + " task in the list";
    }

    public static String showTaskList(ArrayList<Task> tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            int label = i + 1;
            result += "    " + label + ". " + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    public static String showTaskAlreadyExist() {
        return "This task has already exist";
    }

    public static String showInvalidCommand() {
        return "The command entered is invalid.";
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
