package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command{
    public ByeCommand(){
        isExit = true;
    }
    public void execute(TaskList taskList, Storage storage, Ui ui){
        System.out.println(ui.byeMsg());
    }
    public boolean isExit(){
        return isExit;
    }
}
