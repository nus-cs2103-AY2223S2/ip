package crystal;

import crystal.task.Task;
import crystal.task.Todo;
import crystal.task.Deadline;
import crystal.task.Event;
import java.util.Scanner;

/**
 * Represents the Ui task.
 *
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Return the next Line in the file
     *
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message.
     *
     */
    public void showWelcome() {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hi! I am CRYSTAL.\n How may I be of assistance?");
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the error message.
     * @param e Exception
     */
    public void showError(CrystalException e) {
        System.out.println(" ____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the error message if the file failed to load.
     *
     */
    public void showLoadingError() {
        System.out.println("Load Error");
    }

    /**
     * Prints the list.
     * @param task tasklist
     */
    public void printList(TaskList task) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < task.size(); i++) {
            System.out.println(i + 1 + ". " + task.get(i));
        }
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the unmark task message.
     * @param task tasklist.
     * @param num The task number to be unmarked
     *
     */
    public void printUnmark(TaskList task, int num) {
        Task t = task.get(num-1);
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've marked this task as not done: ");
        t.isDone = false;
        System.out.println(t.toString());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the mark task message.
     * @param task tasklist.
     * @param num The task number to be marked
     *
     */
    public void printMark(TaskList task, int num) {
        Task t = task.get(num - 1);
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've marked the task as done: ");
        t.isDone = true;
        System.out.println(t.toString());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the todo task message.
     * @param task tasklist.
     * @param t Todo task
     *
     */
    public void printTodo(TaskList task, Todo t) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've added this task: ");
        System.out.println(t.toString());
        System.out.println("Current number of tasks : " + task.size());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the deadline task message.
     * @param task tasklist.
     * @param d Deadline task.
     *
     */

    public void printDeadline(TaskList task, Deadline d) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've added this task: ");
        System.out.println(d.toString());
        System.out.println("Current number of tasks : " + task.size());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the event task message.
     * @param task tasklist.
     * @param e Event task.
     *
     */
    public void printEvent(TaskList task, Event e) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've added this task: ");
        System.out.println(e.toString());
        System.out.println("Current number of tasks: " + task.size());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the bye message.
     *
     */
    public void printBye() {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Thank You. Please come by again~!");
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the delete message.
     * @param task Tasklist
     * @param num the task number to be deleted
     */
    public void printDelete(TaskList task, int num) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've removed this task: ");
        Task item = task.get(num - 1);
        task.remove(num - 1);
        System.out.println(item.toString());
        System.out.println("Current number of tasks: " + task.size());
        System.out.println(" ____________________________________________________________");
    }
}
