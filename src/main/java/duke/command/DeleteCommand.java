package duke.command;


import java.io.IOException;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * Command that handles deleting a Task
 */

public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Constructor that passes in the number of the task to be deleted
     * @param n
     */
    public DeleteCommand(int n) {
        this.taskNo = n;
    }

    /**
     * Deletes the target task from the TaskList and print out customised message
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            result += ui.changeToFormat("The following task is removed:\n    " + tasks.remove(taskNo));
        } catch (DukeException e) {
            result += e.getMessage();
        }
        try {
            storage.updateLocal(tasks);
        } catch (IOException e) {
            result += ("\nfailed to update tasks locally: " + e.getMessage());
        }
        assert result.equals("-->-->-->-->-->-->-->-->-->-->-->\n    " + "The following task is removed:\n    "
            + tasks.get(taskNo) + "\n<--<--<--<--<--<--<--<--<--<--\n\n") : "wrong delete message";
        return result;
    }
}
