package duke.main;

import duke.task.Task;
import java.util.ArrayList;

/**
 * UI class that deals with input and output for user
 */
public class Ui {
    /**
     * method to print greeting message when Duke starts
     */
    public String printGreeting() {
        return ("Hello! I'm Duke\n" + "What can I do for you?");
    }
    /**
     * method to print bye message when Duke stops
     */
    public String printBye() {
        return ("Bye. Hope to see you again soon!");
    }
    /**
     * method to print the size of given tasklist
     * @param tasklist current tasklist
     */
    public String printTaskNum(Tasklist tasklist) {
        return ("Now you have " + tasklist.getTasks().size() + " tasks in the list.");
    }
    /**
     * method to print notification that new task has been added to the tasklist
     * @param task task to be added
     * @param tasklist current tasklist
     */
    public String printAddTaskMessage(Task task, Tasklist tasklist) {
        return ("Got it. I've added this task:\n" + task + "\n" + printTaskNum(tasklist));
    }
    /**
     * method to print notification that new task has been deleted from the tasklist
     * @param task task to be deleted
     * @param tasklist current tasklist
     */
    public String printDeleteTaskMessage(Task task, Tasklist tasklist) {
        return (" Noted. I've removed this task:\n" + task + "\n" + printTaskNum(tasklist));
    }

    /**
     * method to print out the tasklist
     * @param tasklist current tasklist
     */
    public String printTasks(Tasklist tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task ref = tasks.get(i - 1);
            toReturn.append(i).append(". ").append(ref.toString()).append(System.lineSeparator());
        }
        return toReturn.toString();
    }
    /**
     * method to print notification that target task has been
     * marked as done in the tasklist
     * @param task task to be marked as done
     */
    public  String printMarkTaskMessage(Task task) {
        return ("Nice! This task is marked as done:\n" + task.toString());
    }
    /**
     * method to print notification that target task has been
     * marked as not done in the tasklist
     * @param task task to be marked as not done
     */
    public String printUnmarkTaskMessage(Task task) {
        return ("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    public String printFoundTasks(ArrayList<Task> foundTasks) throws DukeException {
        if (foundTasks.isEmpty()) {
            throw new DukeException("☹ OOPS!!! There are no tasks matching that keyword.");
        }
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= foundTasks.size(); i++) {
            Task ref = foundTasks.get(i - 1);
            toReturn.append(i).append(". ").append(ref.toString()).append(System.lineSeparator());
        }
        return toReturn.toString();
    }

    /**
     * method that prints exception encountered
     * @param e Exception encountered
     */
    public String printException(Exception e) {
        return (e.getMessage());
    }
}
