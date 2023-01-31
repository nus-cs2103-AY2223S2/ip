package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            ui.show("You don't have any tasks now!");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                ui.show((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
