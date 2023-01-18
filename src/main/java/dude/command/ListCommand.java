package dude.command;

import dude.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
