package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

import java.util.ArrayList;

/**
 * The abstract class Command is the superclass of AddCommand, DeleteCommand, ExitCommand, ListCommand, MarkCommand
 * and UnmarkCommand.
 * Subclasses of Command have to provide methods for the execution of the Commands they encapsulate.
 */
public abstract class Command {
    ArrayList<String> tokens;

    /**
     * Abstract constructor of a Command object.
     * @param tokens tokenized user input.
     */
    public Command(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    /**
     * Carries out the sequence of actions necessary to execute this Command.
     *
     * printing or getting input from the user must be done through ui, task management to be carried out
     * through tasks, and reading and writing to/from storage to be handled by storage.
     *
     * Throws a DukeException when errors are encountered during the execution of this command.
     * @param tasks TaskList object to handle task management.
     * @param storage Storage object to handle reading/writing to/from storage.
     * @return string response after execution of command
     * @throws DukeException when errors occur during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * returns whether this Command is an exit command.
     * @return boolean of if this Command is an exit command.
     */
    public abstract boolean isExit();
}
