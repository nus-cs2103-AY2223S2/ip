package Command;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
