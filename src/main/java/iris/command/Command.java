package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;

/**
 * Abstract class to execute commands
 */
public abstract class Command {

    /**
     * Executes the command
     * @param tasks the TaskList for execution of command
     * @param taskStore the TaskStore where TaskList is stored
     * @throws IrisException when input is faulty
     */
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {};

    /**
     * Returns the response for the command
     * @param tasks the TaskList for execution of command
     * @param taskStore the TaskStore where TaskList is stored
     * @return the response
     */
    public abstract String getResponse(TaskList tasks, TaskStore taskStore) throws IrisException;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Command;
    }
}
