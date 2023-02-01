package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
public abstract class Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {};

    public boolean isExit() {
        return false;
    }

}
