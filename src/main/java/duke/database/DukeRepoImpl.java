package duke.database;

import java.util.List;

import duke.exception.DatabaseCloseException;
import duke.task.Task;

/**
 * A implementation class of DukeRepo.
 *
 * @see DukeRepo
 */
public class DukeRepoImpl extends DukeRepo {

    private DukeLocalDatabase local;

    /**
     * Constructs a data layer repo object.
     */
    public DukeRepoImpl() {
        this(false);
    }

    /**
     * Constructs a data layer repo object for testing.
     */
    public DukeRepoImpl(boolean isTestMode) {
        local = new DukeLocalDatabase(isTestMode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAllTask() throws DatabaseCloseException {
        return local.getAllTask();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task getTask(int taskId) throws DatabaseCloseException {
        assert taskId > 0;
        return local.getTask(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task addTask(Task task) throws DatabaseCloseException {
        return local.addTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task updateTask(int taskId, Task task) throws DatabaseCloseException {
        return local.updateTask(taskId, task);
    }

    /**
     * {@inheritDoc}
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
