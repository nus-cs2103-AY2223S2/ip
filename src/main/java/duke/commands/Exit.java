package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class Exit extends Command {
 
    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) {
        ui.showExitMessage();
        System.exit(0);
    }
}
