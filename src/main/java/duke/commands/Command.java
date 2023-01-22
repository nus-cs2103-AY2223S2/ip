package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}
