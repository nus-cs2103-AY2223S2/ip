package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public abstract class Command {
    /**
     * The full command form the user, a <code>String</code>.
     */
    protected String fullCommand;

    /**
     * Constructor for a <code>Command</code>.
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    /**
     * Executes the logic behind this <code>Command</code>.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeException If user input is wrong.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;
    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>false</code>
     */
    public abstract boolean isExit();
    /**
     * Returns the index of task in task list to be marked or unmarked.
     *
     * @param command The 'mark' or 'unmark' command with an index.
     *
     * @return int, the index of the 'mark' or 'unmark' command.
     */
    public static int getMarkIndex(String command) {
        String[] splitted = command.split(" ");
        return Integer.parseInt(splitted[1]);
    }

    /**
     * Returns the keyword of a find <code>Command</code>.
     * @param command The find <code>Command</code> from the user.
     * @return The keyword the user wants to find.
     */
    public static String getFindKeyword(String command) {
        String[] splitted = command.split(" ");
        return splitted[1];
    }
}
