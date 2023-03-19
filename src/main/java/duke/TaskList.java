package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Abstracts the arrayList.
 */
public class TaskList implements Serializable {

    private ArrayList<Task> list;
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Returns the size of the list.
     *
     * @return size of the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds a task into the list.
     *
     * @param task
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index
     * @return the task based on the index number
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Gets a stream from the list.
     *
     * @return the stream of task
     */
    public Stream<Task> stream() {
        return list.stream();
    }

    /**
     * Gets the index based on the given task.
     *
     * @param task
     * @return the index of the task
     */
    public int indexOf(Task task) {
        return list.indexOf(task);
    }

    /**
     * Checks if a given task is already in the list.
     *
     * @param task
     * @return if the task present in the list
     */
    public boolean isExist(Task task) {
        for (Task a: this.list) {
            String s = a.toString();
            s = s.substring(0, 4) + " " + s.substring(5);
            if (task.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the toString.
     *
     * @return the task list
     */
    public String toString() {
        return list.toString();
    }
}
