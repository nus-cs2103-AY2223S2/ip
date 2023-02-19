package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Commands used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public abstract class Command {

    /**
     * Executes the command.
     *  @param tasks
     * @param ui
     * @param storage
     * @return
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command cause duke to terminate.
     *
     * @return boolean value.
     */
    public abstract boolean isExit();
}
