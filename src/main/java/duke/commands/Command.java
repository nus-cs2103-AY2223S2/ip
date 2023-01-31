package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;


/**
 * Superclass for all the commands.
 */
public abstract class Command {
    private int targetIndex;

    /**
     * Constructor.
     */
    public Command() {
    }

    /**
     * Constructor.
     *
     * @param targetIndex Index of the task list to be called on.
     */
    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Gets the target index.
     * @return Target index.
     */
    public int getTargetIndex() {
        return this.targetIndex;
    }

    /**
     * Executes the task.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if command exits or not.
     *
     * @return To be overridden.
     */
    public abstract boolean isExit();
}
