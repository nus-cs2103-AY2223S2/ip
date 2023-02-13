package command;

import task.TaskManager;
import util.DukeException;
import util.DukeUI;

/**
 * Executes find task in list command.
 */
public class FindCommand extends Command {
    private final String word;

    /**
     * Executes command to find a task in the list
     * given an input keyword.
     * <p>
     * @param word
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Finds task in list using keyword provided by user.
     * <p>
     * @param taskManager
     * @return Successful tasks found message
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        assert taskManager != null;
        return DukeUI.foundTaskMessage() + taskManager.findTasks(this.word);
    }
}
