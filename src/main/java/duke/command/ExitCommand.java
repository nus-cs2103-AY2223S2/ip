package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
