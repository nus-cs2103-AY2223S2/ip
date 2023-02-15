package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'mark' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class MarkCommand extends Command {
    /**
     * Constructor for an instance of a <code>MarkCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>MarkCommand</code>, marking a <code>Task</code>.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is wrong.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        int index = Command.getMarkIndex(this.fullCommand) - 1;
        if (index >= tasks.getLength() || index < 0) {
            throw new DukeBadInstructionFormatException("Mark index "
                    + "out of range.");
        }
        storage.fileMarkTask(index);
        tasks.markTask(index);
        String taskDescription = tasks.taskToString(index);
        return ui.showMarkedTask(taskDescription);
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

