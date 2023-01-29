package genie;

import genie.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    // Art generated from: https://patorjk.com/
    private final static String GENIE_LOGO =
                    "                       ______                  _       \n" +
                    "                      / ____/  ___    ____    (_)  ___ \n" +
                    "                     / / __   / _ \\  / __ \\  / /  / _ \\\n" +
                    "                    / /_/ /  /  __/ / / / / / /  /  __/\n" +
                    "                    \\____/   \\___/ /_/ /_/ /_/   \\___/ \n";
    /**
     * A constructor for Ui class.
     */
    public Ui() {}

    /**
     * Prints greet message.
     */
    public void greet() {
        System.out.println("Hello! This is Genie, your personal task tracker!");
    }

    /**
     * Prints Genie logo.
     */
    public void bootLogo() {
        printLine();
        System.out.println(GENIE_LOGO);
        printLine();
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("==============================================================================");
    }

    /**
     * Prints task list if it is not empty. Otherwise, prints an empty task message.
     * @param tasks
     */
    public void printList(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                System.out.println(i + ". " + t.toString());
            }
        } else {
            showEmptyListMessage();
        }
        printLine();
    }

    /**
     * Echos user's input.
     * @return user's input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints mark done message.
     * @param t task to be marked done
     */
    public void showMarkDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " + t.toString());
        printLine();
    }

    /**
     * Prints add task message.
     * @param t task to be added
     * @param size number of tasks in the list
     */
    public void showAddTaskMessage(Task t, int size) {
        System.out.println("Got it. I've added this task:\n  " + t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints unmark done message.
     * @param t task to be unmarked done
     */
    public void showUnmarkDoneMessage(Task t) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + "  " + t.toString());
        printLine();
    }
    /**
     * Prints delete task message.
     * @param t task to be deleted
     * @param size number of tasks in the list
     */
    public void showDeleteTaskMessage(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n" + "  " + t.toString() +
                "\nNow you have " + size + " tasks in the list.");
        printLine();
    }
    /**
     * Prints add farewell message.
     */
    public void showFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Returns general error message.
     * @return error message
     */
    public String showErrorMessage() {
        return "Something went wrong here xx...";
    }

    /**
     * Prints loaded task list from .txt file
     * @param tl task list
     */
    public void printLoadedTaskList(ArrayList<String> tl) {
        if (tl.isEmpty()) {
            showEmptyListMessage();
        } else {
            System.out.println("Here is a record of your task list from where you had previously left off:");
            for (int i = 0; i < tl.size(); i++) {
                System.out.println("  " + tl.get(i));
            }
            System.out.println("Now, what can I do for you?");
        }
        printLine();
    }

    /**
     * Prints empty list message.
     */
    public void showEmptyListMessage() {
        System.out.println("Your task list is currently empty! Let's get started! ^-^");
    }
    public void printMatchingTaskList(ArrayList<Task> tasks) {
        if(tasks.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for(int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                System.out.println(i + ". " + t.toString());
            }
        } else {
            showEmptyMatchingTasksMessage();
        }
        printLine();
    }
    public void showEmptyMatchingTasksMessage() {
        System.out.println("There are no matching tasks for your search :(");
    }
}
