package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
    }
    @Override
    public boolean isGoodbye() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        storage.save(tasks);
        ui.goodbye();
        ui.close();
    }
}
