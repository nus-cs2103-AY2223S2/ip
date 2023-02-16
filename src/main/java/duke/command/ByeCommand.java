package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    public void execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        ui.showGoodbye();
        storage.writeToFile(taskList);
    }

    @Override
    public boolean checkEnd() {
        return true;
    }
}
