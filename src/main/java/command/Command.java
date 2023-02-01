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
    protected int numComponents;

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public Command(String command, boolean doesPrint, int numComponents) throws DukeException {
        this.command = command;
        this.doesPrint = doesPrint;
        this.numComponents = numComponents;
        checkInputComponents();
    }

    /**
     * Executes a command
     *
     * @param taskList the list of tasks
     * @param ui       a text UI
     * @throws DukeException when command invalid
     */
    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        uiPrint(ui, execute(taskList));
    }

    /**
     * Executes a command and returns the message
     * @param taskList the list of tasks
     * @return the reply message from Duke
     * @throws DukeException when command invalid
     */
    public abstract String execute(TaskList taskList) throws DukeException;

    /**
     * Prints to TextUI if print is not suppressed
     * @param ui the Text UI to print to
     * @param toPrint the message to print out
     */
    protected void uiPrint(TextUi ui, String toPrint) {
        if (doesPrint) {
            ui.printStructuredString(toPrint);
        }
    }

    /**
     * Returns whether this is an exit command
     * @return a boolean value indicating status
     */
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

    /**
     * Throws exceptions if the user-input is not a complete command.
     */
    protected void checkInputComponents() throws DukeException {
        String[] splits = command.split(" ");
        if (splits.length < numComponents) {
            throw new DukeException(String.format("Input is not a complete command. "
                            + "It should contain %d components. \nPlease try again. ",
                    numComponents));
        }
    }
}
