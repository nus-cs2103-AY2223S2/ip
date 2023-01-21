package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.InvalidInputException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

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
