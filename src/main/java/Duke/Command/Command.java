package Duke.Command;

import Duke.DukeException.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

public interface Command {
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException;
}
