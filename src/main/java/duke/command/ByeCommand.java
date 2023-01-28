package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'bye' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class ByeCommand extends Command {
    /**
     * Constructor for an instance of a <code>ByeCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public ByeCommand(String fullCommand) {

        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>ByeCommand</code>, exiting Duke.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is not 'bye'
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        if (splitted.length != 1) {
            throw new DukeBadInstructionFormatException("To exit, type *bye*");
        }
        ui.showByeMessage();
    }
    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>true</code>
     */
    @Override
    public boolean isExit() {

        return true;
    }
}
