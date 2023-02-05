package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Finds specific tasks.
 */
public class FindCommand extends Command {
    private String findString;

    /**
     * Constructor for find command.
     *
     * @param findString String to find in tasks.
     */
    public FindCommand(String findString) {
        this.findString = findString;
    }
    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @return Response string.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showMatchingTasks(tasks.filterTasks(this.findString));
    }
}
