package duke.command;

import duke.command.Command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import java.io.IOException;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            ui.unableToSaveErrorMessage();
        } finally {
            ui.exitResponse();
        }
    }
}
