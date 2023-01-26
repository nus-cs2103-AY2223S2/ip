package duke.commands;

import duke.ui.Ui;
import duke.storage.*;
import duke.tasks.*;

import duke.exceptions.DukeException;
public class Command {

    public boolean isExit() {
        return false;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    };
}
