package fea.commands;

import fea.exceptions.FeaException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;

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
     * @return String that contains the tasks which contain the keyword.
     * @throws FeaException If there is an exception due to invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        try {
            String keyword = fullCommand.substring(5);
            return tasks.findTask(keyword, ui);
        } catch (StringIndexOutOfBoundsException e) {
            throw new FeaException("The find command must be in the format: find <keyword>");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
