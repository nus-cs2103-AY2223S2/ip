package command;

import main.TaskList;
import main.Storage;
import main.DukeException;
import main.Ui;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.writeFile(taskList);
        ui.outputExit();
    }

}
