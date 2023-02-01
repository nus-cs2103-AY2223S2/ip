package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import views.UI;

public class ListCommand extends Command {

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            this.commandStatus = "You currently have 0 tasks added. Add one now!";
        } else {
            this.commandStatus = tasks.toString();
        }
        ui.printCommandOutput(this);
    }
}
