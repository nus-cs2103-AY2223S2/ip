package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMesssage(tasks.getTasksList());
    }
}
