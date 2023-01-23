package duke.utilities;

import duke.exceptions.DukeException;
import duke.tasks.ITask;

import java.util.ArrayList;
/**
 * Represents a manager to handle all tasks. A <code>TaskManager</code> object corresponds to
 * a manager handle all task
 */
public class TaskManager {
    private ArrayList<ITask> _tasks;
    private final Storage _storage;

    /**
     * Constructor for TaskManager
     *
     * @param filePath the path to local database file
     */
    public TaskManager(String filePath) {
        _tasks = new ArrayList<>();
        _storage = new Storage(filePath);
    }
    @SuppressWarnings("SuspiciousMethodCalls")
    public int getObjectIndex(Object obj){
        return _tasks.indexOf(obj);
    }


    /**
     * load the tasks from database
     *
     * @throws DukeException IF error occur during loading of task.
     */
    public void load() throws DukeException {
        _tasks = _storage.load();
    }

    /**
     * Return all tasks
     */
    public ArrayList<ITask> getTasks() {
        return _tasks;
    }

    public Object[] find(String keyword) {
        return _tasks.stream().filter(x -> x.descriptionContain(keyword)).toArray();
    }

    /**
     * Return the size of task
     */
    public int size() {
        return _tasks.size();
    }

    /**
     * Remove specific task at given index
     *
     * @param index the index of task to be remove
     */
    public ITask remove(int index) throws DukeException {
        ITask task = _tasks.remove(index);
        _storage.saveAll(_tasks);
        return task;
    }

    /**
     * Add specific task
     *
     * @param task the task to be added to the task list
     */
    public void add(ITask task) throws DukeException {
        _tasks.add(task);
        _storage.saveAll(_tasks);
    }

    /**
     * Mark specific task
     *
     * @param index  the index of task
     * @param isDone status of task
     */
    public ITask mark(int index, boolean isDone) throws DukeException {
        ITask task = _tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUnDone();
        }
        _storage.saveAll(_tasks);

        return task;
    }

}
