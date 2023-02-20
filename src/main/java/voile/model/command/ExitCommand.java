package voile.model.command;

import voile.model.task.TaskList;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList list) {
        return "The library will protect your tasks, come back later to retrieve them.\nGoodbye.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
