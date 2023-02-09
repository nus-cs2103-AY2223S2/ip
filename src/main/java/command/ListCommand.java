package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command{
    public ListCommand(){
    }
    public void execute(TaskList taskList, Storage storage, Ui ui){
        System.out.println(ui.listTasksMsg(taskList));
    }

}
