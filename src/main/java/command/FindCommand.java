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
    public void execute(TaskList taskList, Storage storage, Ui ui){
        System.out.println(ui.findMsg(keyword, taskList));
    }
    @Override
    public boolean isExit(){
       return false;
    }


}
