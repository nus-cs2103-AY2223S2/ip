package iris.command;

import iris.TaskList;
import iris.exception.IrisException;
import iris.Ui;
import iris.TaskStore;

/**
 * Abstract class to execute commands
 */
public abstract class Command {
    /**
     * marks the end of the chat
     * @return true if it's the end of the conversation, else false
     */
    public boolean isEnd() {
        return false;
    }

    /**
     * Executes the command
     * @param tasks the TaskList for execution of command
     * @param ui the Ui execution of command
     * @param taskStore the TaskStore where TaskList is stored
     * @throws IrisException when input is faulty
     */
    public abstract void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Command;
    }
}
