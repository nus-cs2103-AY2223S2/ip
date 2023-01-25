package commands;

import static ui.Ui.LS;

import enums.CommandType;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String s;
    public FindCommand(String s) {
        this.s = s;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ui.display("Here are the matching tasks in your list:" + LS + tl.findList(this.s));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
