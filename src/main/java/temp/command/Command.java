package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public interface Command {
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException;
}
