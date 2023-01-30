package duke;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that abstract the arrayList.
 */
public class TaskList implements Serializable {

    private ArrayList<Task> list;

    /**
     * The constructor of this class.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * The method that returns the size of the list.
     *
     * @return size of the list
     */
    public int size() {
        return list.size();
    }

    /**
     * The method that add a task into the list.
     *
     * @param task
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * The method that remove a task from the list.
     *
     * @param index
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * The method that get a task from the list.
     *
     * @param index
     * @return the task based on the index number
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * The toString method.
     *
     * @return the task list
     */
    public String toString() {
        return list.toString();
    }
}
