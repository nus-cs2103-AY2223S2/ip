package duke.utilities;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.ITask;

/**
 * Represents a manager to handle all tasks.
 * A <code>TaskManager</code> object corresponds to
 * a manager handle all task
 */
public class TaskManager {
    private ArrayList<ITask> tasks;
    private final Storage storage;

    /**
     * Constructor for TaskManager
     *
     * @param filePath the path to local database file
     */
    public TaskManager(String filePath) {
        tasks = new ArrayList<>();
        storage = new Storage(filePath);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public int getObjectIndex(Object obj) {
        return tasks.indexOf(obj);
    }


    /**
     * Loads the tasks from database
     *
     * @throws DukeException IF error occur during loading of task.
     */
    public void load() throws DukeException {
        tasks = storage.load();
    }

    /**
     * Returns all tasks
     */
    public ArrayList<ITask> getTasks() {
        return tasks;
    }

    /**
     * Finds specific task from given keyword
     */
    public Object[] find(String keyword) {
        return tasks.stream()
                .filter(x -> x.descriptionContain(keyword)).toArray();
    }

    /**
     * Returns the size of task
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes specific task at given index
     *
     * @param index the index of task to be remove
     */
    public ITask remove(int index) throws DukeException {
        ITask task = tasks.remove(index);
        storage.saveAll(tasks);
        return task;
    }

    /**
     * Adds specific task
     *
     * @param task the task to be added to the task list
     */
    public void add(ITask task) throws DukeException {
        tasks.add(task);
        storage.saveAll(tasks);
    }

    /**
     * Marks specific task
     *
     * @param index  the index of task
     * @param isDone status of task
     */
    public ITask mark(int index, boolean isDone) throws DukeException {
        ITask task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        storage.saveAll(tasks);

        return task;
    }

}
