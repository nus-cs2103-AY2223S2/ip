package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Models a List command issued.
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        return tasks.returnTaskAsString();
    }

}
