package duke.database;

import java.util.List;
import duke.task.Task;

public abstract class DukeRepo {

    public abstract List<Task> getAllTask();

    public abstract Task getTask(int taskId);

    public abstract Task addTask(Task task);

    public abstract Task updateTask(int taskId, Task task);

    public abstract Task removeTask(int taskId);

    public abstract int count();

    /**
     * Supports safe closure of local database.
     */
    public abstract void close();
}
