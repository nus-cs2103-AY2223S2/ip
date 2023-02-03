package crystal;

import crystal.task.Task;

import java.util.ArrayList;

/**
 * Represents the TaskList class.
 *
 */
public class TaskList {

    public ArrayList<Task> arr;

    /**
     * Constructor for TaskList
     * @param arr Task arraylist
     * @throws CrystalException When the date format is incorrect
     *
     */
    public TaskList(ArrayList<Task> arr) throws CrystalException{
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

    /**
     * Gets the respective task at the index i.
     * @param i Index in the arraylist.
     */
    public Task get(int i) {
        return this.arr.get(i);
    }

    /**
     * Add the new task to the arraylist
     *
     */
    public void add(Task t) {
        this.arr.add(t);
    }

    /**
     * Removes the task from the arraylist.
     *
     */
    public void remove(int i) {
        this.arr.remove(i);
    }

    public String toString() {
        return arr.toString();
    }
}
