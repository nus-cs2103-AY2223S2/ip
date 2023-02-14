package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Find is a command that finds tasks in the task list filtered by a specific keyword.
 */
public class FindCommand extends Command {
    private String[] taskName;
    /**
     * A Constructor for FindCommand.
     *
     * @param taskName Keyword used to filter tasks in the task list.
     */
    public FindCommand(String[] taskName) {
        super(false);
        this.taskName = taskName;
    }

    /**
     * Finds a specific task in the task list using a keyword.
     *
     * @param tasks Task list containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if command cannot be recognised.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.printFindList(tasks.findTaskMatchingKeyword(taskName));
    }
}
