package duke.command;

import java.util.Arrays;

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
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
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

    /**
     * Returns a <code>Task</code>'s description.
     * @param splitted <code>String[]</code> of input
     * @param index End index of description
     * @return Description of <code>Task</code>
     */
    public static String getTaskDescription(String[] splitted, int index) {
        String[] descriptionArray = Arrays.copyOfRange(splitted, 1, index);
        String description = String.join(" ", descriptionArray);
        return description;
    }

    /**
     * Returns an <code>Event</code>'s From.
     * @param splitted <code>String[]</code> of input
     * @param startIndex Start index of from
     * @param endIndex End Index of from
     * @return From of an <code>Event</code>.
     */
    public static String getEventFrom(String[] splitted, int startIndex, int endIndex) {
        String[] fromArray = Arrays.copyOfRange(splitted, startIndex + 1,
                endIndex);
        String from = String.join(" ", fromArray);
        return from;
    }

    /**
     * Returns <code>Deadline</code>'s by or <code>Event</code>'s to
     * @param splitted <code>String[]</code> of input
     * @param startIndex Start index of by or to
     * @return By or to of deadline or event.
     */
    public static String getEventToOrDeadlineBy(String[] splitted, int startIndex) {
        String[] toArray = Arrays.copyOfRange(splitted, startIndex + 1,
                splitted.length);
        String toOrBy = String.join(" ", toArray);
        return toOrBy;
    }
}
