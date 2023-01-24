package Duke.Command;

import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

public class GetAllTask implements Command {

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGetAllTaskResult(tl);
    }
}
