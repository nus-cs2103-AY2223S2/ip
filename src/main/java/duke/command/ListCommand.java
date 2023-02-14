package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand is a command that shows displays the list to user.
 */
public class ListCommand extends Command {

    /**
     * A Constructor for ListCommand
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Shows the list of tasks in the task list to user.
     *
     * @param tasks Task list containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if duke does not recognise command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.showList(tasks);
    }
}
