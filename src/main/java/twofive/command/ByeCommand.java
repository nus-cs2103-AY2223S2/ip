package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            ui.showExit();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
