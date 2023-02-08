package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Handles showing tasks in list of tasks
 */
public class ListCommand extends Command {

    /**
     * Gets list of tasks.
     * Return list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @return List of tasks.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            return "There is no task in your list";
        }
        StringBuilder response = new StringBuilder(ui.getListMessage());
        for (int i = 0; i < tasks.size(); i++) {
            response.append("\n")
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i));
        }
        return response.toString();
    }

}
