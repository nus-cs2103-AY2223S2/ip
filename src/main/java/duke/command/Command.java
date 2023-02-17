package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.DukeException;
import duke.TaskList;
import duke.gui.Ui;

/**
 * Abstract class for duke.commands.
 */
public abstract class Command {

    /**
     * Extracts the tags of the task.
     * @param parts Array of command's words (separated by spaces).
     * @param tagIndex Index of "/tags" in the command.
     * @return An ArrayList of the tags.
     */
    protected ArrayList<String> extractTags(String[] parts, int tagIndex) {
        if (tagIndex == -1) {
            return null;
        }
        return new ArrayList<String>(Arrays.asList(parts).subList(tagIndex + 1, parts.length));
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
