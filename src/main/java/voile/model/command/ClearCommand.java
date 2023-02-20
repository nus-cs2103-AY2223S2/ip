package voile.model.command;

import voile.model.task.TaskList;

/**
 * Represents a command that clear the entire {@code TaskList}.
 */
public class ClearCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        list.clear();
        return "I've cleared your list. Now it is empty!";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClearCommand;
    }
}
