package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
public class UnknownCommand extends Command {

    public UnknownCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
