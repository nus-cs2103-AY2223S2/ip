package duke.command;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Command class that is abstract with multiple subclasses. Every subclass must implement execute() function.
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public abstract class Command {
    // use protected modifier when subclasses need this info
    // private modifier is to encapsulate data within the class itself
    protected String[] contents;

    protected Parser parser;
    protected boolean exitStatus;

    /**
     * Default constructor
     * @param contents Input from the user
     * @param exitStatus 1 for exiting programming, 0 for staying
     */
    public Command(String[] contents, boolean exitStatus) {
        this.contents = contents;
        this.parser = new Parser();
        this.exitStatus = exitStatus;
    }

    /**
     * Returns the contents of the command
     * @return contents of command
     */
    public String[] getContents() {
        return this.contents;
    }

    /**
     * Returns the exit status
     * @return exit status
     */
    public boolean isExit() {
        return this.exitStatus;
    }

    /**
     * Abstract method that allows commands to process the inputs from the user
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When execute() fails due to errors during run time
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
