package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

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
     * The method that get a stream from the list.
     *
     * @return the stream of task
     */
    public Stream<Task> stream() {
        return list.stream();
    }

    /**
     * The method that get the index based on the given task.
     *
     * @param task
     * @return the index of the task
     */
    public int indexOf(Task task) {
        return list.indexOf(task);
    }

    public boolean isExist(Task task) {
        for (Task a: this.list) {
            String s = a.toString();
            s = s.substring(0,4) + " " + s.substring(5);
            if (task.toString().equals(s)) {
                return true;
            }
        }
        return false;
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
