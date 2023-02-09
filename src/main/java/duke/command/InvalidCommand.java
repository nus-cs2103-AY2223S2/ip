package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class InvalidCommand extends Command {


    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return "Invalid command, please try again";
    }

    public boolean isExit() {
        return false;
    }
}
