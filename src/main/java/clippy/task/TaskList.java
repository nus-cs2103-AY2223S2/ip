package clippy.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A list that keeps track of all the Tasks in this program.
 *
 * @author chunzkok
 */
public class TaskList {
    private List<Task> tasks;


    /**
     * Creates an empty TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList instance populated with the given list of Tasks.
     * @param tasks A list of Tasks to populate the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     * @param taskId The ID of the task to be removed.
     * @return The Task that is removed.
     */
    public Task remove(int taskId) {
        return this.tasks.remove(taskId - 1);
    }

    /**
     * Retrieves a Task from the TaskList.
     * @param taskId The ID of the task to be retrieved.
     * @return The Task that is retrieved.
     */
    public Task get(int taskId) {
        return this.tasks.get(taskId - 1);
    }

    /**
     * Retrieves the Task in the last position of the TaskList.
     * @return The Task in the last position of the TaskList.
     */
    public Task getLastTask() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    /**
     * Returns the size of the TaskList.
     * @return The size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns true if the TaskList is empty.
     * @return True if the TaskList is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Marks the specified Task as complete.
     *
     * @param taskId The ID of the task to be marked as complete.
     * @return The Task that was marked as complete.
     */
    public Task mark(int taskId) {
        Task taskToMark = this.get(taskId);
        taskToMark.complete();
        return taskToMark;
    }

    /**
     * Marks the specified task as incomplete.
     * @param taskId The ID of the task to be marked as incomplete.
     * @return The Task that was marked as incomplete.
     */
    public Task unmark(int taskId) {
        Task taskToUnmark = this.get(taskId);
        taskToUnmark.uncomplete();
        return taskToUnmark;
    }

    /**
     * Returns a list of Tasks that the TaskList is tracking.
     * @return A list of Tasks that the TaskList is tracking.
     */
    public List<Task> getList() {
        return this.tasks;
    }
}
