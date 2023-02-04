package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

public class ByeCommand extends Command {
    public ByeCommand() {
        this.commandStatus = "Exit command executed!";
        this.isExit = true;
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        try {
            storage.save();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks to local storage...");
        }
        ui.printCommandOutput(this);
    }
}
