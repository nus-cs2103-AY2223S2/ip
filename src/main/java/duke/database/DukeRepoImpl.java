package duke.database;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import duke.constant.Message;
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
    public List<Task> removeTask(int... taskId) {
        List<Task> res = new ArrayList<Task>();
        for (int i : taskId) {
            try {
                res.add(local.removeTask(i)); 
            } catch (IndexOutOfBoundsException e) {
                System.err.println(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
            }
        }
        return res;
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
