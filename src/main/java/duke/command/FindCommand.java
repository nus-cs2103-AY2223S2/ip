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
     *
     * @param query string from user
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> results = tasks.search(query.split(" "));
        Ui.showList(results, true);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> result = tasks.search(query);
        return Ui.stringList(result, true, true);
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
