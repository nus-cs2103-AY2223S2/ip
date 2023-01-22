package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ClearCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clear();
        ui.showClear();
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}