package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {
    protected boolean terminate = false;

    public boolean isTerminate() {
        return terminate;
    }

    public abstract String execute(TaskList taskList, Storage storage
    , Ui ui) throws DukeMainExceptions;
}
