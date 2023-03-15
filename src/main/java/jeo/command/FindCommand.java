package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

/**
 * Represents a Find Command to get tasks matching a keyword.
 * @author Goh Jun How
 * @version 0.3
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand object.
     * @param keyword string denoting the keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.showTasksWithKeyword(keyword, taskList);
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "find";
    }
}
