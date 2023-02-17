package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> lst;

    /**
     * Creates an empty TaskList.
     */
    TaskList() {
        this.lst = new ArrayList<Task>();
    }

    /**
     * Returns an ArrayList of the tasks in the TaskList.
     * @return An ArrayList of the tasks in the TaskList.
     */
    public List<Task> getTasks() {
        return lst;
    }

    /**
     *
     * Returns the task at the specified position in this list.
     * @param index Index of the task to return
     * @return The element at the index position in this list
     */
    public Task get(int index) {
        return this.lst.get(index);
    }

    /**
     * Appends the specified task to the end of this list.
     * @param task Task to be appended to this list
     * @return This TaskList object
     */
    public TaskList add(Task task) {
        this.lst.add(task);
        return this;
    }

    /**
     * Removes the task at the specified position in this list. Shifts any subsequent task to the left (subtracts one from their indices).
     * @param index the index of the task to be removed
     * @return This TaskList object
     */
    public TaskList remove(int index) {
        this.lst.remove(index);
        return this;
    }

    /**
     * Returns true if this list contains no tasks.
     * @return true if this list contains no tasks.
     */
    public boolean isEmpty() {
        return this.lst.isEmpty();
    }

    /**
     * Returns the number of tasks in this list.
     * @return the number of tasks in this list
     */
    public int size() {
        return this.lst.size();
    }

    public String find(String taskToFind) {
        String response = "";

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getDescription().contains(taskToFind)) {
                response += String.format("%d. %s \n", (i + 1), this.get(i).printTask());
            }
        }
        return response;
    }
    
}
