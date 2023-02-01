package dudu.command;

import dudu.Dudu;
import dudu.exception.DuduException;
import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class to exit the bot.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(null);
    }

    @Override
    public Command execute(TaskList list, Storage storage) throws DuduException {
        System.out.println("Bye. Hope to see you again soon!");
        Dudu.exit();
        return this;
    }
}
