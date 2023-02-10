package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Command to exit the program.
 *
 * @author Samarth Verma
 */
public class Exit extends Command {

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) {
        ui.showExitMessage();
        System.exit(0);
    }
}
