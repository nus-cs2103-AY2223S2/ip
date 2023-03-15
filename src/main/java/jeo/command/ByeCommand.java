package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

/**
 * Represents a Bye Command for exiting the application.
 * @author Goh Jun How
 * @version 0.3
 */
public class ByeCommand extends Command {

    /**
     * Executes the Bye Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.exitMessage();
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "bye";
    }
}
