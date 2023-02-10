package command;

import java.util.ArrayList;

import task.Task;
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
     * @param input
     */
    public FindCommand(String input) {
        this.word = input;
    }

    /**
     * Finds task in list using keyword provided by user.
     * @param taskManager
     * @return Successful tasks found message
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            System.out.println("Here are matching tasks in your list:\n");
            assert taskManager != null;
            ArrayList<Task> arr = taskManager.getTaskArr();
            for (Task task : arr) {
                if (task.getDescription().contains(this.word)) {
                    return task.toString();
                }
            }
            return "";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.missingTaskErrorMessage());
        }
    }
}
