package Commands;

import Exceptions.NoTaskException;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            throw new NoTaskException(null);
        }
        ui.showList();
        ui.showAllTasks(tasks.getMatchingTasks(this.description));
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
