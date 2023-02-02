package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {

    /*
     * Executes the command and exits the program.
     * 
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    /**
     * Returns true to indicate that this is an exit command.
     * 
     * @return true
     */
    public boolean isExit() {
        return true;
    }
}
