package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;


public abstract class Command {
    protected String responseFromDukeAfterExecution = "No response because this has not been executed.";
    /**
     * Executes command specified by user.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Gets response from duke after execution of command
     * @return response
     */
    public String getResponse() {
        return responseFromDukeAfterExecution;
    }
    public boolean isExit() {
        return false;
    }

}
