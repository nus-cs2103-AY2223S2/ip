package duke.Command;

import duke.Storage;
import duke.TaskList;

public class UnknownCommand extends Command {

    public UnknownCommand() {
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        return "Command Unsupported! Sorry :(";
    }

}
