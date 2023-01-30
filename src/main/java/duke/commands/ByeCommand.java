package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class ByeCommand extends Command {

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     * @param ui
     */
    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        ui.showMessage("Bye. Hope to see you again soon!");
        return;
    }

    /**
     * The method to see if the programme should exit.
     *
     * @return a boolean value stating the bot should exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
