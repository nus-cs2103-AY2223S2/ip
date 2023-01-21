package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}
