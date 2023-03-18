package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

/**
 * Represents a List Command to show all tasks.
 * @author Goh Jun How
 * @version 0.3
 */
public class ListCommand extends Command {

    /**
     * Executes the List Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.showAllTasks(taskList);
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "list";
    }
}
