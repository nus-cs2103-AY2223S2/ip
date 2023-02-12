package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.gui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Abstract class for duke.commands.
 */
public abstract class Command {

    /**
     * Extracts the tags of the task.
     * @param parts
     * @param hashIndex
     * @return
     */
    protected ArrayList<String> extractTags(String[] parts, int hashIndex) {
        return new ArrayList<String>(Arrays.asList(parts).subList(hashIndex + 1, parts.length));
    }

    /**
     * Executes the command, calling whatever methods necessary from Ui instance and modifying
     *      the TaskList if required.
     * @param ui The Duke Ui reference.
     * @param list The Duke TaskList reference.
     * @param command The full input command string for verification and further parsing.
     * @return The output of executing the command.
     * @throws DukeException If there is an error in running the command.
     */
    public abstract String execute (Ui ui, TaskList list, String command) throws DukeException;
}
