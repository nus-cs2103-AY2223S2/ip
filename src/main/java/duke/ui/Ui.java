package duke.ui;

import java.util.Scanner;

import duke.TaskList;
import duke.tasks.Task;


import java.util.ArrayList;


public class Ui {

    /**
     * Displays welcome message
     */
    public static void showWelcome() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Hello! I'm duke.Duke\n"
            + "What can I do for you?");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Displays a line
     */
    public static void showLine() {
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Reads a line from the user
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        String cur = sc.nextLine();
        return cur;
    }

    /**
     * Adds a task to the task list
     *
     * @param task The task created
     * @param counter The counter of the number of total tasks
     */
    public void addTask(Task task, int counter) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Displays all the current tasks
     *
     * @param list The task list
     */
    public void listUI(TaskList list) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < list.getLength(); j++) {
            int k = j + 1;
            System.out.println(k + "." + list.getTask(j).toString());
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Marks a task as finished and displays to the user
     *
     * @param task The task finished
     */
    public void mark(Task task) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Displays that the directory is not found
     */
    public static void directoryExceptionUi() {
        System.out.println("____________________________________________________________\n"
            + "   OOPS!!! Directory not found, please create the directory \"data\" first\n"
            + "____________________________________________________________");
    }

    /**
     * Displays that the file is not found
     */
    public static void fileExceptionUi() {
        System.out.println("____________________________________________________________\n"
            + "   OOPS!!! File not found, please create the file \"duke.txt\" first\n"
            + "____________________________________________________________");

    }

    /**
     * Shows error given as the input
     * @param error Error input
     */
    public static void showError(String error) {
        if (!error.equals("wrong")) {
            System.out.println(error);

        } else {
            System.out.println("____________________________________________________________\n"
                + "   OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "____________________________________________________________");

        }
    }


    /**
     * Marks the task as unfinished
     * @param task The task to be marked
     */
    public void unmark(Task task) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Displays bye messgae to the user
     */
    public static void bye() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Deletes a task from the task list
     *
     * @param task The task to be deleted
     * @param counter The counter of the total number of tasks
     */
    public static void delete(Task task, int counter) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------------");
    }


    /**
     * Finds tasks in the task list
     *
     * @param found The indexes of the tasks found in the task list
     * @param list The task list
     */
    public static void find(ArrayList<Integer> found, TaskList list) {
        int counter = 1;
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (Integer cur : found) {
            System.out.println(counter + "." + list.getTask(cur).toString());
            counter += 1;
        }
        System.out.println("------------------------------------------------------------------------------");

    }


}
