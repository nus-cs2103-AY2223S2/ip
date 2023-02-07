package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = "I'm sorry, but I don't know what that means :-(";
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
