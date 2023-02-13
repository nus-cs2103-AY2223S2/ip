package henz.tasklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import henz.henzexception.StorageException;
import henz.tasks.Task;

/**
 * TaskList class extends from {@link ArrayList} of {@link Task} class.
 * @author Leng Wei Cong, Justin
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructor.
     */
    public TaskList() {
        super();
    }

    /**
     * Another constructor.
     * @param tasks the taskList
     */
    public TaskList(TaskList tasks) {
        super.addAll(tasks);
    }

    /**
     * Returns the list of task.
     * @return the list of task
     */
    public List<Task> getList() {
        return Collections.unmodifiableList(this);
    }

    /**
     * Appends task to the end of the list.
     * @param task the task to be added
     * @return true
     */
    public boolean add(Task task) {
        boolean added = super.add(task);

        return added;
    }

    /**
     * Returns task at a specific id.
     * @param id the 1-based id of task to be retrieve
     * @return the task of the specified id
     */
    public Task get(int id) {
        return super.get(id);
    }

    /**
     * Deletes task at a specific id.
     * @param id the 1-based id of task to be removed
     * @return the task removed
     * @throws StorageException
     */
    public Task delete(int id) throws StorageException {
        if (id >= this.size()) {
            throw new StorageException("☹ OOPS!!! delete index does not exist");
        }

        Task task = super.remove(id);

        return task;
    }

    /**
     * Returns the size of the list.
     * @return the size of the list
     */
    public int size() {
        return super.size();
    }
}
