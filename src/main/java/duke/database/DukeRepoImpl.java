package duke.database;

import java.util.List;

import duke.task.Task;

/**
 * A implementation class of DukeRepo
 *
 * @see DukeRepo
 */
public class DukeRepoImpl extends DukeRepo {

    private DukeLocalDatabase local;

    /**
     * Default constructor
     */
    public DukeRepoImpl() {
        this(false);
    }

    /**
     * Constructor for testing
     */
    public DukeRepoImpl(boolean isTestMode) {
        local = new DukeLocalDatabase(isTestMode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAllTask() {
        return local.getAllTask();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task getTask(int taskId) {
        assert taskId > 0;
        return local.getTask(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task addTask(Task task) {
        return local.addTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task updateTask(int taskId, Task task) {
        return local.updateTask(taskId, task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task removeTask(int taskId) {
        assert taskId > 0;
        return local.removeTask(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        local.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count() {
        return local.count();
    }

}
