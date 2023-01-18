package dude.command;

import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks);
        ui.showGoodbye();
        setExit(true);
    }
}
