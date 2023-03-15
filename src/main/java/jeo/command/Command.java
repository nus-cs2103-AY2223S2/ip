package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @return the output message
     */
    public abstract String execute(Ui ui, TaskList taskList);
}
