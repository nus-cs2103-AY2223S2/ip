package command;

import duke.DukeException;
import duke.IncompleteCommandDukeException;

import task.TaskList;
import ui.TextUi;

/**
 * An abstract class for all commands
 */
public abstract class Command {
    protected static String STRING_LIST = "list";
    protected static String STRING_SPACE = " ";

    protected final String command;
    protected final boolean doesPrint;
    protected boolean isExit = false;
    protected int numComponents;

    /**
     * Constructor.
     * @param command the user-input command
     * @param doesPrint whether to print response to console
     * @param numComponents number of non-blank strings separated by blank spaces
     * @throws DukeException when any exceptions occur, but typically related to formatting issues
     */
    public Command(String command, boolean doesPrint, int numComponents) throws DukeException {
        assert numComponents >= 0 : "number of components for any command should be larger than zero";
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
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Get the sub-string after the command key word
     * @param string the command
     * @param commandName the name of the command, such as list and todo
     * @return the sub-string after the command keyword
     * @throws DukeException if command is incomplete
     */
    public String getCommandContent(String string, String commandName) throws DukeException {
        String commandString = commandName.toLowerCase();
        if ((!commandString.equals(STRING_LIST)) && string.length() <= commandString.length() + 1) {
            throw new IncompleteCommandDukeException();
        }
        return string.substring(string.indexOf(commandString) + commandString.length() + " ".length());
    }

    /**
     * Throws exceptions if the user-input is not a complete command.
     *
     * @throws DukeException when the input command is not complete
     */
    protected void checkInputComponents() throws DukeException {
        String[] splits = command.split(STRING_SPACE);
        if (splits.length < numComponents) {
            throw new IncompleteCommandDukeException(String.format("It should contain %d components.", numComponents));
        }
    }
}
