package tasklist;

import java.util.ArrayList;

import task.Task;

/**
 * Encapsulates the list of tasks inputted by the user.
 */
public class TaskList {
    public ArrayList<Task> list;

    /**
     * Constructor.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Returns Task from the index number of the list (zero-indexing).
     *
     * @param index Index number of the task in the list.
     * @return Task of the given index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Removes Task of the given index from the list.
     *
     * @param index Index number of the task in the list.
     */
    public void removeTask(int index) {
        this.list.remove(index);
    }

    /**
     * Add the given Task to the list of tasks.
     *
     * @param task Index number of the task in the list.
     */
    public void addTask(Task task) {
        assert task != null : "task is null";
        this.list.add(task);
    }

    /**
     * Returns the number of the task in the list, whether marked or unmarked.
     *
     * @return Number of tasks in the list
     */
    public int size() {
        int listSize = this.list.size();
        assert listSize >= 0 : "size of list is less than 0";
        return listSize;
    }
}
