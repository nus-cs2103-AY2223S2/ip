package commands;

import exceptions.NoTaskException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class helps to find all matching tasks.
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constructor for the FindCommand.
     * @param userInput The user input.
     */
    public FindCommand(String userInput) {
        assert userInput != null;
        this.description = getDescription(userInput);
    }

    /**
     * Returns the description for initialising the FindCommand.
     * @param userInput The user input.
     * @return The description.
     */
    public String getDescription(String userInput) {
        return userInput.split(" ")[1];
    }

    /**
     * Retrieve and show all tasks that matches the description.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.
     * @return String for executing the command.
     * @throws NoTaskException Throws if no tasks are in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NoTaskException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        if (tasks.isEmpty()) {
            throw new NoTaskException(null);
        }
        return ui.showAllTasks(tasks.getMatchingTasks(this.description));
    }

    /**
     * Check to continue the conversation.
     * @return True.
     */
    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
