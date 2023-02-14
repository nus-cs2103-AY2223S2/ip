package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles showing tasks in list of tasks
 * Gets list of tasks.
 * Return list of tasks.
 */
public class ListCommand extends Command {

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
        StringBuilder response = new StringBuilder(ui.getListMessage());

        for (Task task: tasks.getTasks()) {
            response.append("\n")
                    .append(count + 1)
                    .append(".")
                    .append(task);
            count++;
        }

        setResponse(count == 0
                ? "There is no task in your list"
                : response.toString());
    }
}
