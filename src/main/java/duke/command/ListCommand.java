package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        return tasks.returnTaskAsString();
    }

}
