package duke.command;

import duke.exception.DukeInvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;

public abstract class IndexCommand extends Command{
    protected Task retrieveTask(int index, TaskList task, String command) throws DukeInvalidArgumentException {
        throwExceptionIfInvalidIndex(index, task, command);
        Task t = task.getTaskAt(index);
        return t;
    }

    protected void throwExceptionIfInvalidIndex(int index, TaskList task, String command) throws
            DukeInvalidArgumentException {
        if (index >= task.size()) {
            throw new DukeInvalidArgumentException("There are only " + task.size()
                    + " tasks in list, but want to "
                    + command + " "
                    + getOrdinalFor(index + 1) + " task.");
        }
    }

    protected void throwExceptionIfNegativeIndex(int index, String command) throws DukeInvalidArgumentException {
        if (index < 0) {
            throw new DukeInvalidArgumentException(String.format("Cannot %s %s task", command,
                    getOrdinalFor(index + 1)));
        }
    }
}
