package duke.task;

import java.util.List;

/**
 * List for managing tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskNo) {
        return tasks.remove(taskNo - 1);
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    public Task getTask(int taskNo) {
        return tasks.get(taskNo - 1);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
