package duke.commands;
import duke.dukeexceptions.UnknownCommandException;
import duke.tasklist.TaskList;

/**
 * Command that throws unknown command exception
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super("UNKNOWN");
    }

    @Override
    public String execute(TaskList tasks)throws UnknownCommandException {
        throw new UnknownCommandException();
    }
}
