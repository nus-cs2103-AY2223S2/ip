package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

public class ByeCommand extends Command {
    public ByeCommand() {
        this.commandStatus = "Exit command executed!\n"
                + "I hope you've managed to be productive today. Bye!";
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        try {
            storage.save();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks to local storage...");
        }
    }
}
