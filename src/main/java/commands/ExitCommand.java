package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.bye();
        storage.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
