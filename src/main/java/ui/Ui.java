package ui;

import tasks.Task;
import tasks.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static final String LINE = "-----------------------------------------------------------------------------";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm duke.Duke\n\tWhat can I do for you?");
        System.out.println(LINE + "\n");
    }

    public void taskErrorMsg() {
        System.out.println("\tOOPS!!! Make sure insert all required input.");
    }

    public void idxErrorMsg() {
        System.out.println("\tOOPS!!! Please input the right index.");
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void bye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void addTaskMsg() {
        System.out.println("\t" + "Got it. I've added this task:");
    }

    public void markMsg() {
        System.out.println("\tOK, I've marked this task as done:");
    }

    public void unmarkMsg() {
        System.out.println("\tNice! I've unmarked this task as not done yet:");
    }

    public void printTask(Task task) {
        System.out.println("\t" + task.toString());
    }

    public void removeMsg() {
        System.out.println("\tNoted. I've removed this task:");
    }

    public void invalidCmdMsg() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means");
    }


    public void printListSize(TaskList taskList) {
        System.out.println("\t" + "Now you have " + taskList.getSize() + " tasks in the list");
    }

    public void saveTaskMsg() {
        System.out.println(LINE);
        System.out.println("\tSaved Tasks From Last Session");
    }
}
