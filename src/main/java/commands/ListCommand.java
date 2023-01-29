package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        taskList.listTasks();
    }
}
