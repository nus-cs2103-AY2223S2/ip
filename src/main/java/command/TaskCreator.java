package command;

import aqua.exception.IllegalSyntaxException;
import task.Task;

@FunctionalInterface
public interface TaskCreator {
    public Task apply(CommandInput input) throws IllegalSyntaxException;
}
