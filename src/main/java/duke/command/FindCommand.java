package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command: Finds a list of task that matches the query
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Creates Find Command
     * @param query
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> result = tasks.search(query);
        ui.showList(result);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
