package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * FindCommand class that implements the Command interface.
 */
public class FindCommand implements Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /** 
     * Finds all task in the task list containing the keyword.
     * 
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException If there is an exception due to invalid input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String keyword = fullCommand.substring(5);
            tasks.findTask(keyword, ui);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The find command must be in the format: find <keyword>");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
