package commands;

import java.io.IOException;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import views.UI;

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
