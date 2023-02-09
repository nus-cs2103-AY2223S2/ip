package command;

import storage.Storage;
import tasks.TaskList;
import tasks.Task;
import ui.Ui;

import java.util.Arrays;
import java.util.List;

public class FindCommand extends Command{
    private String keyword;
    private TaskList taskList;
    public FindCommand(String keyword){
        this.keyword = keyword;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        return ui.findMsg(keyword, taskList);
    }
    @Override
    public boolean isExit(){
       return false;
    }


}
