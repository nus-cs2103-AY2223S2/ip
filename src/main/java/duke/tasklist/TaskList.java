package duke.tasklist;

import java.util.ArrayList;
import java.util.Collections;

import duke.storage.Storage;
import duke.task.Task;

/**
 * Class to manage tasks created by user
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    /**
     * Add Task to TaskList
     *
     * @param task Task to be added to TaskList
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * @return size of TaskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * @param index index of task in TaskList (in 0 based index)
     * @return Corresponding task with the index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * @param index Index of task to be removed (in 0 based index)
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * @param index Index of task to be marked as done
     */
    public void mark(int index) {
        this.tasks.get(index).setIsCompleted(true);
    }

    /**
     * @param index Index of task to be marked as undone
     */
    public void unmark(int index) {
        this.tasks.get(index).setIsCompleted(false);
    }

    /**
     * Sorts the task in the tasklist, and then update data.txt to the sorted
     * version of tasks
     *
     * @param storage storage object in charge of stored tasks in data.txt
     */
    public void sortTaskList(Storage storage) {
        Collections.sort(this.tasks);
        for (int i = 0; i < this.tasks.size(); i++) {
            storage.modify(storage.getStorageTaskString(this.tasks.get(i)), i + 1);
        }
    }
}
