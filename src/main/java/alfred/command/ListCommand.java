package alfred.command;

import alfred.task.TaskList;
import alfred.ui.Ui;
import alfred.storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder command = new StringBuilder("Here are your pending tasks: \n");
        if (tasks.isEmpty()) {
            ui.displayCommand("Woohoo! You have no pending tasks\n");
            return;
        }
        String itemList = tasks.getList();
        ui.displayCommand(command.append(itemList).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
