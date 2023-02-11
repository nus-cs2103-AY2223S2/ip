package duke.command;

import java.io.IOException;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command that handles marking a specific task in the TaskList as undone
 */

public class UnmarkCommand extends Command {
    private int taskNo;

    public UnmarkCommand(int n) {
        this.taskNo = n;
    }

    /**
     * on execution, mark the target Task in the TaskList as undone and print out customised message
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            result += ui.changeToFormat("Marked as yet to complete:\n    " + tasks.unmark(taskNo));
        } catch (DukeException e) {
            return e.getMessage();
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            result += "failed to update tasks locally: " + e.getMessage();
        }
        assert result.equals("-->-->-->-->-->-->-->-->-->-->-->\n    " + "Marked as yet to complete:\n    "
            + tasks.get(taskNo) + "\n<--<--<--<--<--<--<--<--<--<--\n\n") : "wrong unmark message";
        return result;
    }
}
