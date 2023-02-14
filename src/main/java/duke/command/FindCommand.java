package duke.command;

import java.util.concurrent.atomic.AtomicInteger;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles the finding tasks that contains the keyword from the list of tasks
 */
public class FindCommand extends Command {
    protected final String keyword;

    /**
     * Stores the specified keyword.
     *
     * @param fullCommand User's input command.
     * @throws DukeException If a keyword is not specified.
     */
    public FindCommand(String fullCommand) throws DukeException {
        try {
            this.keyword = fullCommand.trim()
                    .substring(5)
                    .trim()
                    .toUpperCase();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("A keyword was not given");
        }
    }

    /**
     * Get list of tasks that contains the specified keyword.
     * Return list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 0;
        StringBuilder response = new StringBuilder(ui.getFindMessage());
        for (Task task: tasks.find(keyword)) {
            response.append("\n")
                    .append(count + 1)
                    .append(".")
                    .append(task);
            count++;
        }
        if (count == 0) {
            return "There is no such task in your list";
        }
        return response.toString();
    }
}
