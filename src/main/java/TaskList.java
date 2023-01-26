import java.util.ArrayList;

/**
 * A class to encapsulate task list information & behaviour.
 */
public class TaskList {
    /** The task list */
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList class to create empty list.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList class to load existing list.
     * @param list List of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list.
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        list.remove(task);
    }

    /**
     * Removes the task at a specific index from the list.
     * @param taskIndex Index of task to be removed.
     */
    public void removeTask(int taskIndex) {
        list.remove(taskIndex);
    }

    /**
     * Gets the task stored in a given index.
     * @param taskIndex Index to get task from.
     * @return Task stored at taskIndex.
     */
    public Task getTask(int taskIndex) {
        return list.get(taskIndex);
    }

    /**
     * Gets the size of the current list.
     * @return Size of list.
     */
    public int getSize() {
        return list.size();
    }
}
