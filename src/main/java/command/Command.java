package command;

import duke.DukeException;
import task.TaskList;
import ui.TextUi;

/**
 * An abstract class for all commands
 */
public abstract class Command {
    protected final String command;
    protected final boolean doesPrint;
    protected boolean isExit = false;

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public Command(String command, boolean doesPrint) {
        this.command = command;
        this.doesPrint = doesPrint;
    }

    /**
     * Executes a command
     *
     * @param taskList the list of tasks
     * @param ui       a text UI
     */
    public abstract void execute(TaskList taskList, TextUi ui) throws DukeException;

    protected void uiPrint(TextUi ui, String toPrint) {
        if (doesPrint) {
            ui.printStructuredString(toPrint);
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Get the sub-string after the command key word
     * @param string the command
     * @return the sub-string after the command keyword
     * @throws DukeException
     */
    public String getCommandContent(String string, String commandName) throws DukeException {
        String commandString = commandName.toLowerCase();
        if ((!commandString.equals("list")) && string.length() <= commandString.length() + 1) {
            throw new DukeException("The command argument is not complete.");
        }
        return string.substring(string.indexOf(commandString) + commandString.length() + " ".length());
    }
}
