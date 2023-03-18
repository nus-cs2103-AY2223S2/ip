package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 * @author Goh Jun How
 * @version 0.3
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    public abstract String execute(Ui ui, TaskList taskList);
}
