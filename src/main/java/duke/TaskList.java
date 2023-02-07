package duke;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = Save.loadSave();
    }

    /**
     * Get ArrayList of Task object.
     * @return ArrayList of Task object
     */
    public ArrayList<Task> get() {
        return tasks;
    }

    /**
     * Mark a task done or undone by 0th based index.
     * after marking, make a save to "save" file
     * @param isMark to mark or unmark
     * @param index index of task
     * @return the Task object that is marked
     * @throws IndexOutOfBoundsException index of marked task does not exist
     */
    public Task mark(boolean isMark, int index) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        task.mark(isMark);
        Save.makeSave(tasks);
        return task;
    }

    /**
     * Add Task object.
     * @param task task to be added
     */
    public void add(Task task) {
        tasks.add(task);
        Save.makeSave(tasks);
    }

    /**
     * Delete Task object.
     * @param index index of task to be deleted
     * @return deleted task object
     * @throws IndexOutOfBoundsException deleted task number does not exist
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        Task task = tasks.remove(index);
        Save.makeSave(tasks);
        return task;
    }
}
