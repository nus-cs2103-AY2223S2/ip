package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.TaskException;
public abstract class Command {

    public abstract boolean isExit();
    public abstract void execute(TaskList tasklist, Storage storage, Ui ui) throws TaskException;
}