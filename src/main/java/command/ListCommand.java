package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
    }
    @Override
    public boolean isGoodbye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printTasks(tasks);
    }
}
