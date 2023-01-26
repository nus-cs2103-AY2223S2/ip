package duke.task;

import duke.exception.OutOfBoundsException;

import java.util.ArrayList;
/**
 * Encapsulation of the list containing tasks.
 */
public class TaskList {
    /**
     * An ArrayList to store the tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param taskList The list of our tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns the list of tasks.
     * @return taskList, the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Add tasks into the list and display added task when done.
     * @param task The description of the task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have "
                + this.tasks.size() + " task(s) in your list.\n");
    }

    /**
     * Delete item at the given index.
     * @param index The index of item to be deleted
     */
    public void delete(int index) throws OutOfBoundsException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfBoundsException("Item at given index does not exist! "
                    + "Please enter a valid index.");
        }
        Task removed = this.tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n" + " "
                + removed + "\nNow you have " + this.tasks.size()
                + " task(s) in the list.\n");
    }

    /**
     * Mark the task at the given index as done.
     * @param index The index number of the task given.
     */
    public void markIsDone(int index) throws OutOfBoundsException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfBoundsException("Item at given index does not exist! "
                    + "Please enter a valid index.");
        }
        this.tasks.get(index).markIsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.tasks.get(index) + "\n");
    }

    /**
     * Mark the task at the given index as not done.
     * @param index The index number of the task given.
     */
    public void unmarkIsDone(int index) throws OutOfBoundsException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfBoundsException("Item at given index does not exist! "
                    + "Please enter a valid index.");
        }
        this.tasks.get(index).unmarkIsDone();
        System.out.println("OK, I've marked this task as not done:");
        System.out.println(" " + this.tasks.get(index) + "\n");
    }

    /**
     * Print the list.
     */
    public void print() {
        int size = this.tasks.size();
        if (size == 0) {
            System.out.println("There are no items in the list.\n");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            String toPrint = String.format("%d. %s", (i + 1), this.tasks.get(i));
            System.out.println(toPrint);
        }
        System.out.println("");
    }
}
