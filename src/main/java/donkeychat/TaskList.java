package donkeychat;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> list;

    /**
     * Creates an empty list to store tasks.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds provided task to list.
     *
     * @param toAdd Task to be added to list.
     */
    public void addTask(Task toAdd) {
        list.add(toAdd);
    }

    /**
     * Returns task at provided index.
     *
     * @param i Index at which task is fetched in the list.
     * @return Task at index provided.
     */
    public Task getAtIndex(int i) {
        return list.get(i);
    }

    /**
     * Returns current number of tasks in list.
     *
     * @return Number of tasks in list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Removes the task at the provided index from the list.
     *
     * @param i Index at which task is removed from the list.
     */
    public void removeAtIndex(int i) {
        list.remove(i);
    }

}
