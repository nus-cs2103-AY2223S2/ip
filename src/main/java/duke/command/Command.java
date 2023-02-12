package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;


/**
 * The abstract Command class encapsulates the variables and methods related to commands.
 */
public abstract class Command {
    private final String commandName;

    /**
     * Creates an instance of a Command.
     * @param commandName String name of the command created.
     */
    public Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Executes the command and returns the response as a String.
     * @param taskList the list of tasks the command may manipulate.
     * @param ui an instance of Ui that prints the outcome of a command.
     * @return outcome of command as a String
     * @throws DukeException upon input errors for a command.
     */
    public abstract String execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * Checks whether the command is an instance of Bye command.
     * @return true if the command is an instance of the Bye command and false otherwise.
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
