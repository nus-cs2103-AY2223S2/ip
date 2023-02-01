package duke.main;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class that deals with input and output for user
 */
public class Ui {
    private final Scanner sc;

    /**
     * constructor method that creates a scanner to receive user inputs
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * method to print greeting message when Duke starts
     */
    public void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }

    /**
     * method to print bye message when Duke stops
     */
    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * method to print the size of given tasklist
     * @param tasklist current tasklist
     */
    public void printTaskNum(Tasklist tasklist) {
        System.out.println("Now you have " + tasklist.getTasks().size() + " tasks in the list.");
    }

    /**
     * method to print notification that new task has been added to the tasklist
     * @param task task to be added
     * @param tasklist current tasklist
     */
    public  void printAddTaskMessage(Task task, Tasklist tasklist) {
        printLine();
        System.out.println("Got it. I've added this task:\n" + task);
        printTaskNum(tasklist);
        printLine();

    }
    /**
     * method to print notification that new task has been deleted from the tasklist
     * @param task task to be deleted
     * @param tasklist current tasklist
     */
    public  void printDeleteTaskMessage(Task task, Tasklist tasklist) {
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + task);
        printTaskNum(tasklist);
        printLine();
    }

    /**
     * method to print out the tasklist
     * @param tasklist current tasklist
     */
    public void printTasks(Tasklist tasklist) {
        printLine();
        ArrayList<Task> tasks = tasklist.getTasks();
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString() );
        }
        printLine();
    }
    /**
     * method to print notification that target task has been
     * marked as done in the tasklist
     * @param task task to be marked as done
     */
    public  void printMarkTaskMessage(Task task) {
        printLine();
        System.out.println("Nice! This task is marked as done:\n" + task.toString());
        printLine();
    }
    /**
     * method to print notification that target task has been
     * marked as not done in the tasklist
     * @param task task to be marked as not done
     */
    public  void printUnmarkTaskMessage(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
        printLine();
    }

    public void printFoundTasks(ArrayList<Task> foundTasks) throws DukeException {
        if (foundTasks.isEmpty()) {
            throw new DukeException("☹ OOPS!!! There are no tasks matching that keyword.");
        }
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= foundTasks.size(); i++) {
            System.out.println(i + ". " + foundTasks.get(i - 1).toString() );
        }
        printLine();
    }
    /**
     * method that prints a line to divide messages
     */
    public  void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * method that prints Duke exception encountered
     * @param de Duke exception encountered
     */
    public void printDukeException(DukeException de) {
        printLine();
        System.out.println(de.getMessage());
        printLine();
    }
    /**
     * method that prints IO exception encountered
     * @param ie Input/Output exception encountered
     */
    public void printIOException(IOException ie) {
        printLine();
        System.out.println(" ☹ OOPS!!! IOException occured");
        printLine();
    }

    /**
     * method to read next line of input
     * @return the line of input from user
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * method to close scanner object
     */
    public void closeScanner() {
        this.sc.close();
    }
}
