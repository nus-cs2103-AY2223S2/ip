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
        local = new DukeLocalDatabase();
    }

    /**
     * @see DukeRepo#getAllTask()
     */
    @Override
    public List<Task> getAllTask() {
        return local.getAllTask();
    }

    /**
     * @see DukeRepo#getTask()
     */
    @Override
    public Task getTask(int taskId) {
        return local.getTask(taskId);
    }

    /**
     * @see DukeRepo#addTask()
     */
    @Override
    public Task addTask(Task task) {
        return local.addTask(task);
    }

    /**
     * @see DukeRepo#updateTask()
     */
    @Override
    public Task updateTask(int taskId, Task task) {
        return local.updateTask(taskId, task);
    }

    /**
     * @see DukeRepo#removeTask()
     */
    @Override
    public Task removeTask(int taskId) {
        return local.removeTask(taskId);
    }

    /**
     * @see DukeRepo#close()
     */
    @Override
    public void close() {
        local.close();
    }

    /**
     * @see DukeRepo#count()
     */
    @Override
    public int count() {
        return local.count();
    }

}
