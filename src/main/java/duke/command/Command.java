package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class Command {
    protected final String command;
    public Command() {
        this("");
    }
    public Command(String command) {
        this.command = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // do the commands purpose
        // ui.print() the correct output
        return;
    }
    public boolean isExit() {
        return false;
    }
}
