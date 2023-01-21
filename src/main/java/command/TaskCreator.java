package command;

import aqua.exception.DukeIllegalArgumentException;
import task.Task;

@FunctionalInterface
public interface TaskCreator {
    public Task apply(CommandInput input) throws DukeIllegalArgumentException;
}
