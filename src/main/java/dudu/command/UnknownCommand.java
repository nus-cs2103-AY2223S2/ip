package dudu.command;

import dudu.exception.DuduException;
import dudu.exception.InvalidCommandException;
import dudu.task.TaskList;
import dudu.util.Storage;

public class UnknownCommand extends Command {
    public UnknownCommand(String input) {
        super(input);
    }

    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        throw new InvalidCommandException("Invalid Command");
    }
}
