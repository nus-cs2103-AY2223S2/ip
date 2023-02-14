package spongebob.command;

import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.task.Task;
import spongebob.task.TaskList;

/**
 * Command for indexed command, i.e.: Delete, Mark, Unmark Command.
 */
public abstract class IndexedCommand extends Command {
    protected Task retrieveTask(int index, TaskList task, String command) throws SpongebobInvalidArgumentException {
        throwExceptionIfInvalidIndex(index, task, command);
        return task.getTaskAt(index);
    }

    protected void throwExceptionIfInvalidIndex(int index, TaskList task, String command) throws
            SpongebobInvalidArgumentException {
        if (index >= task.size()) {
            throw new SpongebobInvalidArgumentException("There are only " + task.size()
                    + " tasks in list, but want to "
                    + command + " "
                    + getOrdinalFor(index + 1) + " task.");
        }
    }

    protected void throwExceptionIfNegativeIndex(int index, String command) throws
            SpongebobInvalidArgumentException {
        if (index < 0) {
            throw new SpongebobInvalidArgumentException(String.format("Cannot %s %s task", command,
                    getOrdinalFor(index + 1)));
        }
    }
}
