package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;

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
