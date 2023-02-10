package duke.database;

import java.util.List;

import duke.exception.DatabaseCloseException;
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
     * @throws DatabaseCloseException
     */
    public abstract List<Task> getAllTask() throws DatabaseCloseException;

    /**
     * Retrives an entry from database by id
     *
     * @param taskId int
     * @return {@link Task} object
     * @throws DatabaseCloseException
     */
    public abstract Task getTask(int taskId) throws DatabaseCloseException;

    /**
     * Add an entry to database
     *
     * @param task {@link Task} object
     * @return {@link Task} object
     * @throws DatabaseCloseException
     */
    public abstract Task addTask(Task task) throws DatabaseCloseException;

    /**
     * Update an entry from database by id
     *
     * @param taskId int
     * @return {@link Task} object
     * @throws DatabaseCloseException
     */
    public abstract Task updateTask(int taskId, Task task) throws DatabaseCloseException;

    /**
     * Remove an entry from database by id
     *
     * @param taskId
     * @return {@link Task} object
     * @throws DatabaseCloseException
     * @throws IndexOutOfBoundsException
     */
    public abstract List<Task> removeTask(int... taskId) throws IndexOutOfBoundsException, DatabaseCloseException;

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
