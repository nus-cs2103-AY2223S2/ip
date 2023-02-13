package duke.commands;
import duke.database.Database;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the abstract Command to be inherited from by all Commands.
 */
public abstract class Command {

    /** Frame to be used in response. */
    public static final String FRAME = ""; //"    ____________________________________________________________\n";

    /** Changed to false only in the case of a bye command. */
    private boolean isActive;

    public Command() {
        this.isActive = true;
    }

    /**
     * Execute method which will be run immediately after the generation of the command. Implementation
     * depends on the command being run.
     *
     * @param taskList taskList of Duke.
     * @param ui user interface object of Duke.
     * @param database database of Duke.
     * @throws DukeException throws a Duke related exception depending on the command generated.
     */
    public abstract void execute(TaskList taskList, Ui ui, Database database) throws DukeException;

    public boolean isActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
