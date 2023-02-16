package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {
    public Command() {
    }

    public abstract String executeCommand(Storage storage, TaskList tasks) throws DukeException;
}
