package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand class that handles the actions of the delete command
 */
public class DeleteCommand extends Command {
    protected int taskNumber;

    /**
     * Creates a new DeleteCommand
     * @param taskNumber which task number to be removed
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Action to be performed by the delete command
     * @param tasks the TaskList class that keeps track of the tasks
     * @param ui User Interface controlling what gets shown on the screen
     * @param storage Storage class that handles the file input and output (saving)
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.delete(taskNumber);

    }
    /**
     * Tests if at end of command stack
     * @return false if not at end, true if no more commands left
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
