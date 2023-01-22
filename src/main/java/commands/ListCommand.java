package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
