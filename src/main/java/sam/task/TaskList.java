package sam.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the task with the specified id.
     * If the id is invalid, returns null.
     * 
     * @param id The id of the task to return.
     * @return The Task object with the specified id.
     */
    public Task getTask(int id) {
        if (isValidId(id)) {
            return tasks.get(id - 1);
        }
        return null;
    }

    /**
     * Appends the specified task to the list.
     * 
     * @param task The task to be appended.
     * @return {@code true}
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    /**
     * Removes the task with the specified id from the list and return it.
     * If the id is invalid, returns null.
     * 
     * @param id The id of the task to remove.
     * @return The task that was removed from the list.
     */
    public Task removeTask(int id) {
        if (isValidId(id)) {
            return tasks.remove(id - 1);
        }
        return null;
    }

    /**
     * Sets the task with the specified id as done or not done.
     * 
     * @param id     The id of the task to mark.
     * @param isDone Indicates whether the task is done.
     * @return {@code true} if successful.
     */
    public boolean setTaskDone(int id, boolean isDone) {
        if (isValidId(id)) {
            getTask(id).setDone(isDone);
            return true;
        }
        return false;
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int count() {
        return tasks.size();
    }

    /**
     * Generates a numbered string array of all tasks in the list.
     * 
     * @return A string array of the tasks.
     */
    public List<String> getTasks() {
        List<String> list = new ArrayList<>(count());
        for (int i = 0; i < count(); i++) {
            Task task = tasks.get(i);
            list.add(String.format("%d: %s", i + 1, task));
        }
        return list;
    }

    /**
     * Generates a numbered string array of the tasks containing the specified substring.
     * 
     * @return A string array of the tasks.
     */
    public List<String> findTasks(String subString) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count(); i++) {
            Task t = tasks.get(i);
            if (t.title.contains(subString)) {
                list.add(String.format("%d: %s", i + 1, t));
            }
        }
        return list;
    }

    private boolean isValidId(int id) {
        return !(id <= 0 || id > count());
    }
}
