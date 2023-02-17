package duke.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * The TaskList class implements a task list which can store tasks.
 * It provides methods to add, delete and get tasks as well as return the size of the task list.
 * The TaskList class also provides a method to convert the list of tasks into an array of strings.
 *
 * @author owen-yap
 */
public class TaskList implements Serializable {
    private final List<Task> tasks;
    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks the list of tasks to store
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Adds a task to the TaskList.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }
    /**
     * Deletes a task at a specified index from the TaskList.
     *
     * @param i the index of the task to delete
     */
    public void delete(int i) {
        this.tasks.remove(i);
    }
    /**
     * Retrieves a task at a specified index from the TaskList.
     *
     * @param i the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }
    /**
     * Returns the size of the TaskList.
     *
     * @return the number of tasks in the TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds tasks that contain the query in their description or name and returns
     * an array of string representation of the tasks even if the query matches only partially.
     *
     * @param query the string to search for in the task description or name
     * @return an array of string representation of the tasks that contain the query
     */
    public String[] find(String query) {
        List<Task> res = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.contains(query)) {
                res.add(t);
            }
        }

        String[] list = new String[res.size()];
        Task t;
        for (int i = 0; i < res.size(); i++) {
            t = res.get(i);
            list[i] = String.format("%s", t);
        }
        return list;
    }

    /**
     * Converts the list of tasks into an array of strings.
     *
     * @return the array of strings representing the tasks
     */
    public String[] toStringArray() {
        String[] list = new String[this.tasks.size()];
        Task tsk;
        for (int i = 0; i < this.tasks.size(); i++) {
            tsk = this.tasks.get(i);
            list[i] = String.format("%s", tsk);
        }
        return list;
    }

}
