package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class CommandNotFound extends Command {

    public CommandNotFound() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandNotFound();
    }
}
