package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}
