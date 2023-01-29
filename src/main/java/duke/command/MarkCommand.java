package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand is a command that marks a task as done.
 */
public class MarkCommand extends Command {
    int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index Index of task to be marked as done.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Marks the task at the specific index in the tasklist as done.
     *
     * @param task Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.markTask(index);
        ui.showMark(task.getTask(index));
        storage.saveTasksToFile(task.getListOfTasks());
    }
}
