package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'delete' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for an instance of a <code>DeleteCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>DeleteCommand</code>, deletes the
     * <code>Task</code> at the specified index.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is wrong
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        int index = Command.getMarkIndex(this.fullCommand) - 1;

        if (index >= tasks.getLength()) {
            throw new DukeBadInstructionFormatException("Delete index"
                    + "out of range.");
        }
        storage.fileDeleteTask(index);
        Task removed = tasks.delete(index);
        return ui.showDeletedTask(removed, tasks);

    }
    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>false</code>
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
