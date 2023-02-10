package kuromi.task;

import java.util.ArrayList;

/**
 * List of tasks represented by an ArrayList of Tasks.
 */
public class TaskList {
    /** ArrayList of current tasks **/
    private ArrayList<Task> tasks;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor (for invocation if file in hard disk is not present)
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor (for invocation if TaskList is not empty)
     *
     * @param tasks Tasks as ArrayList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Mark a task with number idx.
     *
     * @param idx Index of the task to be marked.
     * @return Marked task.
     */
    public Task mark(int idx) {
        this.tasks.get(idx).mark();
        return this.tasks.get(idx);
    }

    /**
     * Unmark a task with number idx.
     *
     * @param idx Index of the task to be unmarked.
     * @return Unmarked task.
     */
    public Task unmark(int idx) {
        this.tasks.get(idx).unmark();
        return this.tasks.get(idx);
    }

    /**
     * Delete a task with number idx.
     *
     * @param idx Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task delete(int idx) {
        Task cur = this.tasks.get(idx);
        this.tasks.remove(idx);
        return cur;
    }

    /**
     * Add a task to the current ArrayList.
     *
     * @param task Task to be added.
     * @return Added task.
     */
    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Get the task with number idx.
     *
     * @param idx Index of the task.
     * @return Task.
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Get number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Get the TaskList that contains the task with the keyword given.
     *
     * @param keyword The keyword to be searched.
     * @return TaskList of current tasks filtered by the keyword.
     */
    public TaskList find(String keyword) {
        TaskList res = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task cur = this.tasks.get(i);
            if(cur.containsKeyword(keyword)) {
                res.add(cur);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String msg = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            msg += (i + 1) + ". ";
            msg += this.tasks.get(i);
            msg += "\n";
        }
        return msg;
    }
}
