package genie.command;

import genie.main.Storage;
import genie.main.TaskList;
import genie.main.Ui;

public class ListCommand extends Command {
    public ListCommand() {}
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList.getTasks());
    }
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
