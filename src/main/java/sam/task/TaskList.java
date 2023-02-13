package sam.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import sam.parser.SamInvalidDateException;
import sam.parser.SamInvalidTaskException;

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
        if (!isValidId(id)) {
            return null;
        }
        return tasks.get(id - 1);
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
        if (!isValidId(id)) {
            return null;
        }
        return tasks.remove(id - 1);
    }

    /**
     * Sets the task with the specified id as done or not done.
     *
     * @param id     The id of the task to mark.
     * @param isDone Indicates whether the task is done.
     * @return {@code true} if successful.
     */
    public Task setTaskDone(int id, boolean isDone) {
        if (!isValidId(id)) {
            return null;
        }
        Task task = getTask(id);
        task.setDone(isDone);
        return task;
    }

    /**
     * Updates the task with the specified id.
     *
     * @param id      The id of the task to update.
     * @param argsMap A Map of the task arguments to replace.
     * @return {@code true} if successful.
     * @throws SamInvalidDateException If a date string is in the wrong format.
     */
    public Task updateTask(int id, Map<String, String> argsMap) throws SamInvalidDateException {
        if (!isValidId(id)) {
            return null;
        }
        Task task = getTask(id);
        task.update(argsMap);
        return task;
    }

    /**
     * Clones the task with the specified id.
     *
     * @param id      The id of the task to update.
     * @param argsMap A Map of the task arguments to replace.
     * @return {@code true} if successful.
     * @throws SamInvalidDateException If a date string is in the wrong format.
     * @throws SamInvalidTaskException If the task cloning fails.
     */
    public Task cloneTask(int id, Map<String, String> argsMap)
            throws SamInvalidDateException, SamInvalidTaskException {
        if (!isValidId(id)) {
            return null;
        }
        try {
            Task task = (Task) getTask(id).clone();
            task.update(argsMap);
            addTask(task);
            return task;
        } catch (CloneNotSupportedException e) {
            throw new SamInvalidTaskException();
        }
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
     * Returns a read-only view of tasks.
     *
     * @return A read-only view of tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns a list of tasks that match the given substring.
     * Tasks that do not match are replaces with null.
     *
     * @param subString The string to find.
     * @return A list of tasks that match the given substring.
     */
    public List<Task> findTasks(String subString) {
        List<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            if (task.matchTitle(subString)) {
                list.add(task);
            } else {
                list.add(null);
            }
        }
        return list;
    }

    private boolean isValidId(int id) {
        return !(id <= 0 || id > count());
    }
}
