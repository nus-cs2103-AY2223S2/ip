package chad.backend;

import chad.tasks.Task;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;


/**
 * Class storing all the tasks stored in Duke.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage saveManager;

    /**
     * Constructor for a TaskList.
     * @throws IOException
     */
    public TaskList() throws IOException {
        //  load from file
        File prevTasks = new File("./tasks.txt");
        prevTasks.createNewFile();
        this.saveManager = new Storage(prevTasks);
        this.tasks = new ArrayList<>(saveManager.extractTasks());
    }

    /**
     * Add a new task to the TaskList.
     * @param t The Task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Getter for TaskList Tasks.
     * @param idx 0-Index of the task to get.
     * @return The Task at that index.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Marks a Task as done.
     * @param idx 0-Index of the Task to be marked.
     * @return Task at index <code>idx</code>, marked as done.
     */
    public Task mark(int idx) {
        Task t = tasks.get(idx);
        t.mark();
        return t;
    }

    /**
     * Marks a Task as not done.
     * @param idx 0-Index of the Task to be unmarked.
     * @return Task at index <code>idx</code>, marked as not done.
     */
    public Task unmarkIdx(int idx) {
        Task t = tasks.get(idx);
        t.unmark();
        return t;
    }

    /**
     * Deletes the Task at a particular index.
     * @param idx The 0-index of the Task to be deleted.
     * @return The deleted Task object.
     */
    public Task delete(int idx) {
        Task t = tasks.get(idx);
        tasks.remove(idx);
        return t;
    }

    /**
     * Get the whole list of Tasks as an Array.
     * @return An ArrayList of Tasks.
     */
    public ArrayList<Task> getWholeList() {
        return this.tasks;
    }

    /**
     * Saves the tasks to a .TXT file via the Storage object.
     */
    public void closeAndSave() {
        this.saveManager.saveTasks(this.tasks);
    }
}
