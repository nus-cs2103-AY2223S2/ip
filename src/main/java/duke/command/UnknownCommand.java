package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Models an unknown command that is issued.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        return "Command Unsupported! Sorry :(";
    }

}
