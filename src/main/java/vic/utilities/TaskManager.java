package vic.utilities;

import java.util.ArrayList;

import vic.commands.ICommand;
import vic.exceptions.DukeCloneException;
import vic.exceptions.DukeException;
import vic.tasks.ITask;

/**
 * Represents a manager to handle all tasks.
 * A <code>TaskManager</code> object corresponds to
 * a manager handle all task
 */
public class TaskManager {
    private ArrayList<ITask> tasks;
    private final Storage storage;
    private LastCommand lastTask = null;

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
     * Returns all tasks by using stream filter
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
     * @param index the index of task to be removed
     */
    public ITask remove(int index) throws DukeException {

        ITask task = tasks.remove(index);
        updateLastTask(ICommand.Type.DELETE, task);

        storage.saveAll(tasks);
        return task;
    }

    /**
     * Undoes the last action to the task list
     * and returns the feedback result
     */
    public String undo() throws DukeException {
        String result;
        if (lastTask == null) {
            result = "No command capture in history";
        } else {
            result = lastTask.undo(tasks);
            lastTask = null;
        }
        storage.saveAll(tasks);

        return result;
    }

    /**
     * Adds specific task
     *
     * @param task the task to be added to the task list
     */
    public void add(ITask task) throws DukeException {
        updateLastTask(ICommand.Type.TODO, task);
        tasks.add(task);
        storage.saveAll(tasks);
    }

    /**
     * Marks specific task
     *
     * @param index  the index of task
     */
    public ITask mark(int index) throws DukeException {
        ITask task = tasks.get(index);

        updateLastTask(ICommand.Type.MARK, task);
        task.markAsDone();

        return task;
    }

    /**
     * Un-marks specific task
     *
     * @param index  the index of task
     */
    public ITask unmark(int index) throws DukeException {
        ITask task = tasks.get(index);

        updateLastTask(ICommand.Type.UNMARK, task);
        task.markAsUndone();

        storage.saveAll(tasks);

        return task;
    }

    private void updateLastTask(ICommand.Type type, ITask task) throws DukeException {
        try {
            lastTask = new LastCommand(type, (ITask) task.clone());
        } catch (CloneNotSupportedException e) {
            throw new DukeCloneException(e.getMessage());
        }
    }

}
