/**
 * Abstract class for duke.commands.
 */
public abstract class Command {
    /**
     * Executes the command, calling whatever methods necessary from Ui instance and modifying
     *      the TaskList if required.
     * @param ui The Duke Ui reference.
     * @param list The Duke TaskList reference.
     * @param command The full input command string for verification and further parsing.
     * @throws DukeException If there is an error in running the command.
     */
    public abstract void execute (Ui ui, TaskList list, String command) throws DukeException;
}
