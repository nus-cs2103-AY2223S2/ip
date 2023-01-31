package duke;

/**
 * Find a task by searching for a keyword entered by the user.
 */
public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(String[] fullCommand) throws DukeEmptyArgumentException {
        try {
            keyword = fullCommand[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of delete command cannot be empty.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException, DukeIOException {
        TaskList taskList = storage.findDataFromFile(keyword);
        return ui.responseToFindTaskCommand(taskList);
    }
}
