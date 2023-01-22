package command;

import java.util.Iterator;

import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * Command to find tasks given a query string.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor for FindCommand.
     * @param query The query string.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Finds tasks with a description that contains the query string.
     * @param tasks The existing task list.
     * @param ui The ui of Duke chat.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Iterator<Task> resultsIterator = tasks.find(this.query).iterator();
        if (!resultsIterator.hasNext()) {
            ui.showSuccess("There are no matching tasks in your list:");
            return;
        }

        ui.showSuccess("Here are the matching tasks in your list:");

        int taskNo = 1;
        while (resultsIterator.hasNext()) {
            Task task = resultsIterator.next();
            ui.showSuccess(taskNo + ". " + task.toString());
            taskNo += 1;
        }
    }

    /**
     * Determines if the current command is an exit command.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
