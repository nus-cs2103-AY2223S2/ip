package runner;
import task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for taskList to store tasks.
 */
public class TaskList {
    protected List<Task> taskList;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Return a task specified by the index.
     * @param n Index of the task.
     */
    public Task getTask(int n) {
        assert n < taskList.size():"Index Invalid";
        return taskList.get(n);
    }

    /**
     * Return the List<Task> inside.
     */
    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Return the size of a TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Add one Task to TaskList.
     * @param tk Task added.
     */
    public void add(Task tk) {
        taskList.add(tk);
    }

    /**
     * Insert one Task to TaskList.
     * @param tk Task to be inserted.
     * @param n Position to the list.
     */
    public void insert(Task tk, int n) {
        taskList.add(n, tk);
    }

    /**
     * Remove the task from the TaskList.
     * @param n Index of the task.
     */
    public void remove(int n) {
        assert n >= 0: "Index Invalid";
        taskList.remove(n);
    }
}
