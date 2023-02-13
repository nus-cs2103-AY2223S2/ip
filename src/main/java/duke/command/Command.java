package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public static boolean checkAlias(String alias) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    };

    public boolean isExit() {
        return false;
    }
}
