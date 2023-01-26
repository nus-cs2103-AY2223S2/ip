package duke.helper;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final String line = "_______________________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void showErrorMsg(String type) {
        System.out.println(String.format("%s commands need to be followed by an integer!", type));
    }

    public static void showErrorMsg(int size) {
        System.out.println(String.format("Sorry but there are only %d tasks stored!", size));
    }

    public String[] getNextLine() {
        showLine();
        String input = scanner.nextLine();
        String[] splitStr = input.split(" ", 2);
        showLine();
        return splitStr;
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showExit() {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    public static void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void showMark(boolean isDone, Task taskToMark) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskToMark);
    }

    public void showTaskOutput(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
