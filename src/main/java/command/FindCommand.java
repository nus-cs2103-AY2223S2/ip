package command;

import java.util.Iterator;

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
     * @return The return status of the result from executing this command in the form of a text message.
     */
    @Override
    public String execute(TaskList tasks) {
        Iterator<Task> resultsIterator = tasks.find(this.query).iterator();

        if (!resultsIterator.hasNext()) {
            return "There are no matching tasks in your list...";
        }

        StringBuilder response = new StringBuilder();

        response.append("Here are the matching tasks in your list:\n\n");

        int taskNo = 1;
        while (resultsIterator.hasNext()) {
            Task task = resultsIterator.next();
            response.append(taskNo + ". " + task + "\n");
            taskNo += 1;
        }

        return response.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String undo(TaskList tasks) {
        /* Just a placeholder. undo is not applicable here. */
        return null;
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
