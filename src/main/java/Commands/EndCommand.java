package Commands;

import java.io.IOException;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class EndCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showFarewell();
        storage.store(tasks);
    }

    @Override
    public boolean isContinueConvo() {
        return false;
    }
}
