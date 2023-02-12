package duke.command;

import java.io.IOException;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that marks or unmarks a task in the task list.
 */
public class MarkCommand extends Command {
    private final int markNumber;
    private final boolean isMark;

    /**
     * Represents a constructor for the MarkCommand object.
     *
     * @param markNumber Number of the task to be marked or unmarked.
     * @param isMark True if the task is to be marked, false if the task is to be unmarked.
     */
    public MarkCommand(int markNumber, boolean isMark) {
        this.markNumber = markNumber;
        this.isMark = isMark;
    }

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and marks or unmarks a task in the task list.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     * @throws DukeException If there is an error in executing the command.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        if (tl.isEmpty()) {
            throw new DukeException("How to mark or unmark an empty list of tasks meowww");
        }
        if (this.markNumber > tl.size() || this.markNumber < 1) {
            throw new DukeException("Out of range you can mark or unmark!");
        }
        String toShow;
        if (isMark) {
            toShow = tl.markTask(markNumber - 1);
            ui.showToUser(toShow);
        } else {
            toShow = tl.unmarkTask(markNumber - 1);
            ui.showToUser(toShow);
        }
        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in delete");
        }
        return toShow;
    }
}
