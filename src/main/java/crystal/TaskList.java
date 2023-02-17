package crystal;

import java.util.ArrayList;

import crystal.task.Task;



/**
 * Represents the TaskList class.
 *
 */
public class TaskList {

    private ArrayList<Task> arr;

    /**
     * Constructor for TaskList
     * @param arr Task arraylist
     * @throws CrystalException When the date format is incorrect
     *
     */
    public TaskList(ArrayList<Task> arr) throws CrystalException {
        this.arr = arr;
    }

    /**
     * Constructor for TaskList.
     *
     */
    public TaskList() {

        this.arr = arr;
    }

    /**
     * Returns the size of the arraylist.
     *
     */
    public int size() {
        return arr.size();
    }


    //@@author amoonguss1-reused
    //Reused from https://github.com/amoonguss1/ip/blob/master/src/main/java/Nerd/entities/TaskList.java
    // with minor modifications
    /**
     * Gets the respective task at the index i.
     * @param i Index in the arraylist.
     */
    public Task get(int i) {
        assert i >= 0 : "Index cannot be negative";
        return this.arr.get(i);
    }

    /**
     * Add the new task to the arraylist
     *
     */
    public void add(Task t) {
        this.arr.add(t);
    }

    //@@author amoonguss1-reused
    //Reused from https://github.com/amoonguss1/ip/blob/master/src/main/java/Nerd/entities/TaskList.java
    // with minor modifications
    /**
     * Removes the task from the arraylist.
     *
     */
    public void remove(int i) {
        assert i >= 0 : "Index cannot be negative";
        this.arr.remove(i);
    }

    public String toString() {
        return arr.toString();
    }

}

