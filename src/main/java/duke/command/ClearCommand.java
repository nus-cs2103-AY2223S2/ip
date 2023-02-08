package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'clear' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class ClearCommand extends Command {
    /**
     * Constructor for an instance of a <code>ClearCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public ClearCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>ClearCommand</code>, deleting all
     * <code>Task</code>s in <code>TaskList</code>.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is not 'clear'
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        if (splitted.length != 1) {
            throw new DukeBadInstructionFormatException(
                    "Usage of *clear* command: clear");
        }
        assert splitted[0].equals(Parser.CLEAR_STRING) : "Wrong command made a clear";
        tasks.clearAllTasks();
        storage.clearAllTasks();
        return ui.showClearTasksMessage();
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
