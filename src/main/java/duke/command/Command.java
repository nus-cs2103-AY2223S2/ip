package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;


public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException, IOException;


    public abstract boolean isExit();
}
