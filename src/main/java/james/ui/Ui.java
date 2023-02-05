package james.ui;

import james.JamesException;
import james.task.Task;
import james.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user interface class.
 * Handles all user's input and output.
 */
public class Ui {
    private Scanner scan = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public void welcome() {
        System.out.println("Hello! I'm James.");
        System.out.println("How can I help you today?");
    }

    /**
     * Prints the message when users exit.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you soon!");
        scan.close();
    }

    /**
     * Prints the message when a task is added.
     *
     * @param task      The task that was added.
     * @param taskSize  The number of tasks in the task list.
     */
    public void addTask(Task task, int taskSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    /**
     * Prints the message when a task is marked.
     *
     * @param task  The task that is to be marked.
     */
    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the message when a task is unmarked.
     *
     * @param task  The task that is to be unmarked.
     */
    public void unmarkTask(Task task) {
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(task);
    }

    /**
     * Prints the message when a task is erased.
     *
     * @param task  The task that is to be erased.
     * @param taskSize  The number of tasks in the task list after deletion.
     */
    public void eraseTask(Task task, int taskSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    /**
     * Prints the taskList.
     *
     * @param taskList  The taskList of tasks to be printed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("" + (i + 1) + "." + t);
        }
    }

    /**
     * Prints the error message.
     *
     * @param e  The error message to be printed.
     */
    public void printError(JamesException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Reads the command from user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scan.nextLine();
    }

    public void printFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            Task t = foundTasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
    }

}
