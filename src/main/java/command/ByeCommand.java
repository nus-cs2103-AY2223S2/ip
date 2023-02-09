package command;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command{
    public ByeCommand(){
        isExit = true;
    }
    public String execute(TaskList taskList, Storage storage, Ui ui){
        return ui.byeMsg();
    }
    public boolean isExit(){
        return isExit;
    }
}
