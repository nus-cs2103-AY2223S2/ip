package Command;

import Main.TaskList;
import Main.Storage;
import Main.DukeException;
import Main.Ui;

public abstract class Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {};

    public boolean isExit() {
        return false;
    }

}
