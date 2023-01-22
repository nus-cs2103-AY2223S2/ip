package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class InvalidCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
