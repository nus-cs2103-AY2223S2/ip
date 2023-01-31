package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.sayBye();
        storage.saveTasks(tasks);
    }
}
