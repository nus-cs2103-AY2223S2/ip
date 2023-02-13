package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

public class FreeCommand extends Command {

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        this.commandStatus = tasks.findFreeDay();
    }
}
