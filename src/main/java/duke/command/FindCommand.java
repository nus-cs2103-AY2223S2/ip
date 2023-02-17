package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to find items in the list
 */
public class FindCommand extends Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return taskList.findItem(input);
    }
}
