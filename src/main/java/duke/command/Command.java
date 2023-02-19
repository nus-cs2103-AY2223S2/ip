package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    public boolean isExit() {
        return false;
    }
}