package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.DukeException;

public abstract class Command {

    /**
     * Executes the command according to its specifications.
     *
     * @param tasks The current list of tasks.
     * @param ui Ui displaying the results of execution.
     * @param storage Storage to update when tasks update.
     * @throws DukeException When the task cannot be executed or has an error.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the Command is an ExitCommand.
     *
     * @return true if Command is an ExitCommand, false otherwise.
     */
    public abstract boolean isExit();

    @Override
    public abstract String toString();
}
