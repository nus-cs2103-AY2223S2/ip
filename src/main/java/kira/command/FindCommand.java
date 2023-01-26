package kira.command;

import kira.storage.TaskList;
import kira.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasklist) {
        ui.findMsg(tasklist.findByKey(this.keyword), this.keyword);
        return true;
    }
    
    

}
