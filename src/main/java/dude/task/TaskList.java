package dude.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of Tasks
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initializes TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes TaskList.
     *
     * @param tasks List of task to store into TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets Task in TaskList at given task index.
     *
     * @param taskIndex Index of Task.
     * @return Task at given task index.
     */
    public Task getTask(int taskIndex) {
        assert taskIndex > 0 : "Task index should be more than 0";
        return tasks.get(taskIndex - 1);
    }

    /**
     * Adds Task into TaskList.
     *
     * @param task Task object to be added into TaskList.
     */
    public void addTask(Task task) {
        assert task != null : "New task should not be null";
        tasks.add(task);
        Task.addTaskCount();
    }

    /**
     * Deletes Task from TaskList.
     *
     * @param taskIndex Index of Task to be deleted from TaskList.
     */
    public void deleteTask(int taskIndex) {
        assert taskIndex > 0 : "Task index should be more than 0";
        tasks.remove(taskIndex - 1);
        Task.decreaseTaskCount();
    }

    /**
     * Returns raw output of TaskList.
     *
     * @return Raw output of TaskList.
     */
    public String toRaw() {
        StringBuilder input = new StringBuilder();
        for (Task task : tasks) {
            input.append(task.toRaw());
        }
        return input.toString();
    }

    /**
     * Returns string output of TaskList.
     *
     * @return String output of TaskList.
     */
    @Override
    public String toString() {
        StringBuilder result;
        assert tasks.size() > 0 : "Number of tasks should be more than 0";
        if (tasks.size() != 0) {
            result = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            }
        } else {
            result = new StringBuilder("Eh... You currently got no task leh.\n");
        }
        return result.toString();
    }
}
