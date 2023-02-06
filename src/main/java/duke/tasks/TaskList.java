package duke.tasks;

import java.util.ArrayList;

/**
 * A wrapper class for a list of tasks.
 */
public class TaskList {
    /** ArrayList to store the tasks. **/
    private final ArrayList<Task> taskList;

    /**
     * A Constructor for a TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        assert this.isEmpty() : "Failed to add task";
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        assert index < 0 : "Index is invalid";
        return this.taskList.get(index);
    }

    /**
     * Deletes a task from this TaskList based on its index.
     * @param index Index of the task in 1-based indexing format
     * @return Task that is deleted
     */
    public Task delete(int index) {
        assert index < 1 : "Index is invalid";
        return this.taskList.remove(index - 1);
    }

    /**
     * Marks a task as done in this TaskList based on its index.
     * @param index Index of the task in 1-based indexing format
     */
    public void markDone(int index) {
        assert index < 1 : "Index is invalid";
        Task task = this.taskList.get(index - 1);
        task.markAsDone();
    }

    /**
     * Marks a task as not done in this TaskList based on its index.
     * @param index Index of the task in 1-based indexing format
     */
    public void markUndone(int index) {
        assert index < 1 : "Index is invalid";
        Task task = this.taskList.get(index - 1);
        task.markAsUndone();
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("List of Tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1))
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return result.toString();
    }
}
