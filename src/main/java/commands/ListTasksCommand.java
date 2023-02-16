package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class ListTasksCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printResponse(taskList.getSizeAsString() + taskList.getAllAsString());
    }
}
