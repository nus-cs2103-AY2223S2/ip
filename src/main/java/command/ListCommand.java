package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command{
    public String execute(TaskList taskList, Storage storage, Ui ui){
        return ui.listTasksMsg(taskList);
    }

}
