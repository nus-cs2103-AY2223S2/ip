package sam.command;

import sam.SamException;
import sam.storage.Storage;
import sam.task.TaskList;
import sam.ui.Ui;


/**
 * Represents a user command.
 */
public abstract class Command {
    protected String args;

    /**
     * Constructs a new Command.
     *
     * @param args The command string.
     */
    public Command(String args) {
        this.args = args;
    }

    /**
     * Executes the command.
     *
     * @param tasks The affected TaskList.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @throws SamException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws SamException;
}
