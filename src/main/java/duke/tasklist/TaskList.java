package duke.tasklist;
import java.util.ArrayList;
import duke.tasks.Task;

/**
 * Encapsulates a list of Tasks.
 */
public class TaskList {
    /** An ArrayList of Tasks.*/
    ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    /**
     * Constructs a new TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Finds a task based upon search key-words provided by the user.
     * @param searchWord The search key-words from the user.
     * @return A task with a matching key word.
     */
    public ArrayList<Task> findTask(String searchWord) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task is null";
            if (task.provideDetails().contains(searchWord)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Stores a new task.
     * @param task The task to be stored.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task as completed.
     * @param number The number representing the task to be updated.
     */
    public void mark(int number) {
        this.tasks.get(number - 1).mark();
    }

    /**
     * Marks a task as uncompleted.
     * @param number The number representing the task to be updated.
     */
    public void unmark(int number) {
        this.tasks.get(number - 1).unmark();
    }

    /**
     * Deletes a task.
     * @param number The number representing the task to be updated.
     */
    public void deleteTask(int number) {
        this.tasks.remove(number - 1);
    }

    /**
     * Gets the number of tasks.
     * @return Number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Gets a task.
     * @param number An integer describing the task.
     * @return A task.
     */
    public Task getTask(int number) {
        return this.tasks.get(number - 1);
    }
}

