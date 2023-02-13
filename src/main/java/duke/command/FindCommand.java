package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Find a task by searching for a keyword entered by the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor to create a find command.
     *
     * @param fullCommand user input.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an e empty argument.
     */
    public FindCommand(String[] fullCommand) throws DukeEmptyArgumentException {
        try {
            keyword = fullCommand[1];
            assert keyword != null : "Find command with empty keyword";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of delete command cannot be empty.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException, DukeIoException,
            DukeEventOverlapException {
        TaskList taskList = storage.findDataFromFile(keyword);
        return ui.responseToFindTaskCommand(taskList);
    }
}
