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
     * Converts the list of tasks into an array of strings.
     *
     * @return the array of strings representing the tasks
     */
    public String[] toStringArray() {
        String[] list = new String[this.tasks.size()];
        Task tsk;
        for (int i = 0; i < this.tasks.size(); i++) {
            tsk = this.tasks.get(i);
            list[i] = String.format(" %s", tsk);
        }
        return list;
    }

}
