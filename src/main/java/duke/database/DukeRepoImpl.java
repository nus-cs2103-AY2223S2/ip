package duke.database;

import java.util.List;

import duke.exception.DatabaseCloseException;
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
     * @throws DatabaseCloseException
     */
    @Override
    public List<Task> getAllTask() throws DatabaseCloseException {
        return local.getAllTask();
    }

    /**
     * {@inheritDoc}
     * @throws DatabaseCloseException
     */
    @Override
    public Task getTask(int taskId) throws DatabaseCloseException {
        assert taskId > 0;
        return local.getTask(taskId);
    }

    /**
     * {@inheritDoc}
     * @throws DatabaseCloseException
     */
    @Override
    public Task addTask(Task task) throws DatabaseCloseException {
        return local.addTask(task);
    }

    /**
     * {@inheritDoc}
     * @throws DatabaseCloseException
     */
    @Override
    public Task updateTask(int taskId, Task task) throws DatabaseCloseException {
        return local.updateTask(taskId, task);
    }

    /**
     * {@inheritDoc}
     * @throws DatabaseCloseException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public List<Task> removeTask(int... taskIds) throws IndexOutOfBoundsException, DatabaseCloseException {
        return local.removeTask(taskIds);
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
