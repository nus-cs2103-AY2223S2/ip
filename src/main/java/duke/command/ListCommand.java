package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.Storage;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}