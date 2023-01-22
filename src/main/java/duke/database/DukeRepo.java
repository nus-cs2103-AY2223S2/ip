package duke.database;

import java.util.List;

import duke.task.Task;

/**
 * An abstract Repo class that defines the interface for data layer
 * implementations.
 */
public abstract class DukeRepo {

    /**
     * Retrives all entries from database
     *
     * @return {@link Task} List
     */
    public abstract List<Task> getAllTask();

    /**
     * Retrives an entry from database by id
     *
     * @param taskId int
     * @return {@link Task} object
     */
    public abstract Task getTask(int taskId);

    /**
     * Add an entry to database
     *
     * @param task {@link Task} object
     * @return {@link Task} object
     */
    public abstract Task addTask(Task task);

    /**
     * Update an entry from database by id
     *
     * @param taskId int
     * @return {@link Task} object
     */
    public abstract Task updateTask(int taskId, Task task);

    /**
     * Remove an entry from database by id
     *
     * @param taskId
     * @return {@link Task} object
     */
    public abstract Task removeTask(int taskId);

    /**
     * The number of entries in database.
     *
     * @return int
     */
    public abstract int count();

    /**
     * Supports safe closure of local database.
     */
    public abstract void close();
}
