package duke.command;

import duke.exception.DukeUnknownInputException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates an unknown <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for an instance of an <code>UnknownCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public UnknownCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Throws a <code>DukeUnknownInputException</code>.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeUnknownInputException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeUnknownInputException {
        throw new DukeUnknownInputException("Unknown input.");

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
