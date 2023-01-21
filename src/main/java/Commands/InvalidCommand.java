package Commands;

import Exceptions.DukeException;
import Exceptions.InvalidInputException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

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
