package command;

import exception.DukeException;
import main.Storage;
import main.Ui;
import main.TaskList;

public interface Command {
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}
