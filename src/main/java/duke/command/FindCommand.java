package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deals with finding tasks that contains the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Finds tasks that contains the keyword.
     *
     * @param fullCommand User's input command.
     * @throws DukeException If a keyword is not specified.
     */
    public FindCommand(String fullCommand) throws DukeException {
        try {
            this.keyword = fullCommand.trim()
                    .substring(5)
                    .trim();
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskMissingKeyword();
        }
    }

    /**
     * Executes command input by user.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int count = 0;
        StringBuilder response = new StringBuilder(ui.getFindMessage());

        for (Task task: tasks.find(keyword)) {
            response.append("\n")
                    .append(count + 1)
                    .append(".")
                    .append(task);
            count++;
        }

        setResponse(count == 0
                ? "There is no such task in your list."
                : response.toString());
    }
}
