package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'find' <code>Command</code> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class FindCommand extends Command {
    /**
     * Constructor for an instance of a <code>FindCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the logic behind <code>FindCommand</code>, listing all <code>Task</code>s
     * that contains a keyword.
     * @param tasks The <code>TaskList</code> associated with Duke.
     * @param ui The <code>Ui</code> associated with Duke.
     * @param storage The <code>Storage</code> associated with Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = Command.getFindKeyword(this.fullCommand);
        tasks.findInTaskList(keyword);
    }

    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
