package duke.task;

import java.util.ArrayList;

import duke.exception.OutOfBoundsException;

/**
 * Encapsulates the related fields and behavior of the list containing tasks.
 */
public class TaskList {
<<<<<<< HEAD
    //An ArrayList to store the tasks.
    private ArrayList<Task> taskList;
=======
    /**
     * An ArrayList to store the tasks.
     */
    private ArrayList<Task> tasks;
>>>>>>> branch-A-CodingStandard

    /**
     * Instantiates TaskList with no arguments given.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates TaskList with the given ArrayList.
     *
     * @param taskList The list of our tasks fetched from hard drive storage.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns this list of tasks.
     *
     * @return The ArrayList containing the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds tasks into the list and prints out completion message when done.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have "
                + this.tasks.size() + " task(s) in your list.\n");
    }

    /**
     * Deletes task at the given index.
     *
     * @param index The index of task to be deleted.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
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
     * Marks the task at the given index as done.
     *
     * @param index The index number of the task given.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
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
     * Marks the task at the given index as not done.
     *
     * @param index The index number of the task given.
     * @throws OutOfBoundsException If index given is less than 0
     *                              or more than the index of the last list element.
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
     * Prints all the tasks in the list.
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
