package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.exception.ColetteException;
import colette.gui.GuiText;

/** Class that represents a user command */
public abstract class Command {

    /** Whether the Colette chatbot should exit */
    private boolean isExit;

    /**
     * Constructs a Command object.
     *
     * @param isExit Whether this command
     *               exits the Colette chatbot.
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
     * @throws ColetteException if execution fails.
     */
    public abstract String execute(TaskList tasks, GuiText guiText, Storage storage) throws ColetteException;

    public boolean isExit() {
        return this.isExit;
    }

}
