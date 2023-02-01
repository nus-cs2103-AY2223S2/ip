import java.util.ArrayList;

/**
 * Contains the task list
 * Has operations to man tasks in the list
 */
public class TaskList {
    ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(TaskList tasks) {
        this.tasks = tasks.getTasks();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds specified task to list of tasks
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes specified task from list of tasks
     *
     * @param index Index of task to be removed
     */
    public Task delete(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Marks specified task from list of tasks as done
     *
     * @param index Index of specified task
     */
    public Task mark(int index) {
        Task task = tasks.get(index - 1);
        task.mark();
        return task;
    }

    /**
     * Changes the status of specified task from list of tasks back to not done
     *
     * @param index Index of specified task
     */
    public Task unmark(int index) {
        Task task = tasks.get(index - 1);
        task.unmark();
        return task;
    }

    /**
     * Shows the list of tasks
     */
    public Task[] toArray() {
        return tasks.toArray(new Task[0]);
    }
}
