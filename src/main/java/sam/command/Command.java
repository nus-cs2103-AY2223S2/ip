package sam.command;

import sam.SamException;
import sam.storage.Storage;
import sam.task.TaskList;


/**
 * Represents a user command.
 */
public abstract class Command {
    protected String args;
    protected Result result;

    /**
     * Constructs a new Command.
     *
     * @param args The command string.
     */
    public Command(String args) {
        this.args = args;
        result = new Result();
    }

    /**
     * Executes the command.
     *
     * @param tasks The affected TaskList.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @throws SamException
     */
    public abstract Result execute(TaskList tasks, Storage storage)
            throws SamException;
}
