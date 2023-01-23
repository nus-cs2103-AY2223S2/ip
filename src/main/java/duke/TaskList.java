package duke;
import java.util.ArrayList;
class TaskList {
    private ArrayList<Task> tasks;
    TaskList() {
        tasks = Save.loadSave();
    }

    /**
     * Get ArrayList<Task> object.
     * @return ArrayList<Task> object
     */
    public ArrayList<Task> get() {
        return tasks;
    }

    /**
     * Mark a task done or undone by 0th based index.
     * 
     * after marking, make a save to "save" file
     * 
     * @param isMark
     * @param index
     * @return the Task object that is marked
     * @throws IndexOutOfBoundsException
     */
    public Task mark(boolean isMark, int index) throws IndexOutOfBoundsException{
        Task task = tasks.get(index);
        task.mark(isMark);
        Save.makeSave(tasks);
        return task;
    }

    /**
     * Add Task object.
     * 
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
        Save.makeSave(tasks);
    }

    /**
     * delete Task object
     * @param index
     * @return deleted task object
     * @throws IndexOutOfBoundsException
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        Task task = tasks.remove(index);
        Save.makeSave(tasks);
        return task;
    }
}