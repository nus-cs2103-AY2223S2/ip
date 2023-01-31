package Command;

import Main.TaskList;
import Main.Storage;
import Main.DukeException;
import Main.Ui;

public class ListCommand extends Command {


    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.writeFile(taskList);
        ui.outputListTask(taskList);
    }

}
