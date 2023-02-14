package chattime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chattime.task.Task;

/**
 * Represents a list object to store user's tasks.
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a taskList object with ArrayList as data structure.
     *
     * @param initialTasks The stored data loaded from storage.
     */
    public TaskList(ArrayList<Task> initialTasks) {
        tasks = initialTasks;
    }

    /**
     * Adds a new task to current task list.
     *
     * @param task The new task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes index-th task object from current task list.
     *
     * @param index The input index from user, index-th task in task list to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Gets the index-th task from the task list.
     *
     * @param index The input index from user.
     * @return The index-th task in task list.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Gets the current entire task list.
     *
     * @return The current task list.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Gets the current entire task list.
     *
     * @param taskType The task type code.
     * @return The current task list.
     */
    public boolean isDuplicates(Task testTask, Class<?> taskType) {
        List<Task> filteredList = tasks.stream()
                .filter(task -> task.getClass().equals(taskType)).collect(Collectors.toList());

        for (Task task : filteredList) {
            if (testTask.isDuplicate(task)) {
                return true;
            }
        }
        return false;
    }
}
