package duke.database;

import java.util.List;
import duke.task.Task;

public class DukeRepoImpl extends DukeRepo {

    private DukeLocalDatabase local;

    public DukeRepoImpl() {
        local = new DukeLocalDatabase();
    }

    @Override
    public List<Task> getAllTask() {
        return local.getAllTask();
    }

    @Override
    public Task getTask(int taskId) {
        return local.getTask(taskId);
    }

    @Override
    public Task addTask(Task task) {
        return local.addTask(task);
    }

    @Override
    public Task updateTask(int taskId, Task task) {
        return local.updateTask(taskId, task);
    }

    @Override
    public Task removeTask(int taskId) {
        return local.removeTask(taskId);
    }

    @Override
    public void close() {
        local.close();
    }

    @Override
    public int count() {
        return local.count();
    }

}
