package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.InvalidCommandException;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class for not recognised command
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for unknown command
     * @param input Unrecognised command that is inputted
     */
    public UnknownCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList list, Storage storage) throws DuduException {
        throw new InvalidCommandException("Invalid Command");
    }
}
