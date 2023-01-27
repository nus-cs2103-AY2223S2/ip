package command;

import duke.DukeException;
import task.TaskList;
import ui.TextUi;

/**
 * An abstract class for all commands
 */
public abstract class CommandClass {
    protected final String command;
    protected final boolean doesPrint;
    protected final boolean isExit;

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public CommandClass(String command, boolean doesPrint, boolean isExit) {
        this.command = command;
        this.doesPrint = doesPrint;
        this.isExit = isExit;
    }

    /**
     * Executes a command
     *
     * @param taskList the list of tasks
     * @param ui       a text UI
     */
    abstract public void execute(TaskList taskList, TextUi ui) throws DukeException;

    protected void uiPrint(TextUi ui, String toPrint) {
        if (doesPrint) {
            ui.printStructuredString(toPrint);
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
