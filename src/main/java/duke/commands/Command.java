package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {

    /**
     * Executes the command.
     * Prints appropriate error messages.
     *
     * @param tasks TaskList
     * @param ui The Ui object
     * @param storage The Storage object used to save the edited TaskList.
     * @return True if the execution is successful, false if it's not.
     */
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Overridden by ByeCommand only.
     *
     * @return True if the program needs to terminate, false if it does not.
     */
    public boolean isExit(){
        return false;
    }
}

