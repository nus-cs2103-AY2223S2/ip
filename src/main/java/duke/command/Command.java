package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class Command {

    protected String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;

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
