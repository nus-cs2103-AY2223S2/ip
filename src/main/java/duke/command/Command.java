package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.gui.GuiText;

/** Class that represents a user command */
public abstract class Command {

    /** Whether Duke should exit */
    private boolean isExit;

    /**
     * Constructs a Command object.
     *
     * @param isExit Whether this command
     *               exits Duke.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes this command.
     *
     * @param tasks Current task list.
     * @param guiText User interface.
     * @param storage Storage.
     * @throws DukeException if execution fails.
     */
    public abstract String execute(TaskList tasks, GuiText guiText, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

}
