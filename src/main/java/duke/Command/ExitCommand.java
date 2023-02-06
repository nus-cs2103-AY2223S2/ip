package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        exit = true;
        try {
            storage.store(tasks);
            ui.showStored(tasks);
        } catch (IOException ignored) {
            ui.showLoadingError();
        }
        ui.showGoodbye();
        return "";
    }
}
