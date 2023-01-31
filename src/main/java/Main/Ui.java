package Main;

import java.util.Scanner;

import Task.Task;

public class Ui {

    private static final String indentation = "     ";
    private static final String newLine = "    ____________________________________________________________";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void greet() {
        System.out.println(newLine);
        System.out.println(indentation + "Hello! I'm Main.Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(newLine);
    }

    public static void outputExit() {
        System.out.println(indentation + "Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(newLine);
    }

    public void outputAddTask(Task t) {
        System.out.println(indentation + "Got it. I've added this task:");
        System.out.println(indentation + t);
    }

    public void outputUnmark(Task t) {
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + t);
    }

    public void outputMark(Task t) {
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + t);
    }

    public void outputDeleteTask(Task t) {
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + t);
    }

    public void outputListTask(TaskList taskList) {
        System.out.println(indentation + "You have " + taskList.getTotalNumberOfTask() + " tasks in the list.");
        for (int i = 0; i < taskList.getTotalNumberOfTask(); i++ ) {
            System.out.println(indentation + (i + 1) + "." + taskList.getTaskAtIndex(i));
        }
    }

    public void outputError(String message) {
        System.out.println(indentation + message);
    }
}
