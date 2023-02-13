package duke.command;

import java.io.IOException;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command that handles marking a specific Task as done
 */
public class MarkCommand extends Command {
    private final int taskNo;

    /**
     * Constructor that takes in the number of the Task to be marked
     * @param n
     */
    public MarkCommand(int n) {
        this.taskNo = n;
    }

    /**
     * Marks the target Task in the TaskList as done and print out customised message
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            result += ui.changeToFormat("Marked as completed:\n    " + tasks.mark(taskNo));
        } catch (DukeException e) {
            return e.getMessage();
        }
        try {
            storage.updateLocal(tasks);
        } catch (IOException e) {
            result += "failed to update tasks locally: " + e.getMessage();
        }
        return result;
    }
}
