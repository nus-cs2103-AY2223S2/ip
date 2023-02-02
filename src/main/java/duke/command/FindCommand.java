package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the finding tasks that contains the keyword from the list of tasks
 */
public class FindCommand extends Command {
    protected final String keyword;

    /**
     * Stores the specified keyword
     *
     * @param fullCommand User's input command
     * @throws DukeException If a keyword is not specified
     */
    public FindCommand(String fullCommand) throws DukeException {
        try {
            this.keyword = fullCommand.trim()
                    .substring(5)
                    .trim();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("A keyword was not given");
        }
    }

    /**
     * Shows the list of tasks that contains the specified keyword
     *
     * @param tasks List of tasks
     * @param ui Handles user interaction
     * @param storage Handles saving and loading tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind(tasks, keyword);
    }
}
