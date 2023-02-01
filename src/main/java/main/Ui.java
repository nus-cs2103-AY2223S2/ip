package main;

import java.util.Scanner;

import task.Task;

/**
 * Class the takes input from user and output messages to user.
 */
public class Ui {

    private static final String indentation = "     ";
    private static final String newLine = "    ____________________________________________________________";
    private final Scanner sc;

    /**
     * Constructs Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Greats user when programme starts.
     */
    public static void greet() {
        System.out.println(newLine);
        System.out.println(indentation + "Hello! I'm Main.Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(newLine);
    }

    /**
     * Output goodbye messages to users when programme ends.
     */
    public static void outputExit() {
        System.out.println(indentation + "Bye. Hope to see you again soon!");
    }

    /**
     * Reads commands.
     *
     * @return Message that user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Output a black line.
     */
    public void showLine() {
        System.out.println(newLine);
    }

    /**
     * Outputs details of the task that is added.
     * @param t Task that is added.
     */
    public void outputAddTask(Task t) {
        System.out.println(indentation + "Got it. I've added this task:");
        System.out.println(indentation + t);
    }

    /**
     * Outputs details of the task that is marked as done.
     * @param t Task that is marked as done.
     */
    public void outputTaskDone(Task t) {
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + t);
    }

    /**
     * Outputs details of the task that is marked as not done.
     * @param t Task that is marked as not done.
     */
    public void outputTaskNotDone(Task t) {
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + t);
    }

    /**
     * Outputs details of the task that is deleted.
     * @param t Task that is deleted.
     */
    public void outputDeleteTask(Task t) {
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + t);
    }

    /**
     * Outputs details of all the tasks.
     * @param taskList List of tasks.
     */
    public void outputListTask(TaskList taskList) {
        System.out.println(indentation + "You have " + taskList.getTotalNumberOfTask() + " tasks in the list.");
        for (int i = 0; i < taskList.getTotalNumberOfTask(); i++ ) {
            System.out.println(indentation + (i + 1) + "." + taskList.getTaskAtIndex(i));
        }
    }

    /**
     * Outputs details of why an exception is raised.
     * @param message Details of why an exception is raised.
     */
    public void outputError(String message) {
        System.out.println(indentation + message);
    }
}
