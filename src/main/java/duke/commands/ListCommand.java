package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

public class ListCommand extends Command {

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            this.commandStatus = "You currently have 0 tasks added. Add one now!";
        } else {
            this.commandStatus = tasks.toString();
        }
    }
}
