package duke.command;

import duke.TaskList;
import duke.gui.Ui;

/**
 * A Command subclass for the bye command.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList list, String command) {
        // Do nothing.
        return "";
    }
}
