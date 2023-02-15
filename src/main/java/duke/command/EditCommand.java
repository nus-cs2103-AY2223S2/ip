package duke.command;

import java.io.IOException;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that edits a task in the task list.
 */
public class EditCommand extends Command {
    private final int taskNumber;
    private final Task task;

    /**
     * Represents a constructor for the EditCommand object.
     *
     * @param markNumber Number of the task to be marked or unmarked.
     * @param isMark True if the task is to be marked, false if the task is to be unmarked.
     */
    public EditCommand(int taskNumber, Task task) {
        this.taskNumber = taskNumber;
        this.task = task;
    }

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and edits a task in the task list.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     * @throws DukeException If there is an error in executing the command.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        if (tl.isEmpty()) {
            throw new DukeException("How to edit an empty list of tasks meowww");
        }
        if (this.taskNumber > tl.size() || this.taskNumber < 1) {
            throw new DukeException("Out of range you can edit!");
        }
        String toShow = "Meowwww, I've edited this task for you.\n" + tl.get(this.taskNumber - 1);
        toShow += "\n meow Changed into: " + task;
        tl.replaceTask(this.taskNumber - 1, task);
        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in edit");
        }
        return toShow;
    }
}
