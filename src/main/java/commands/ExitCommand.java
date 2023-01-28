package commands;

import components.Storage;
import components.TaskList;
import components.Ui;
import exceptions.DukeException;

import java.util.ArrayList;

public class ExitCommand extends Command {

    public ExitCommand(ArrayList<String> tokens) {
        super(tokens);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showExitMsg();
        storage.updateStorage(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
