package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
public class ListCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
