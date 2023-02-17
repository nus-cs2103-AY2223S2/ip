package duke.commands;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;
import duke.logic.TaskList;
import duke.logic.Ui;
import duke.logic.Storage;

/**
 * Represents a command that can be executed by the program as a result of user input.
 * @author lukkesreysandeur
 */
public abstract class Command {
    /** The entered user input. */
    protected String input;

    /**
     * Initialises the command object.
     *
     * @param input The given user input.
     */
    public Command(String input) {
        assert input != null;
        this.input = input;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return true if the command is an exit command;
     *         false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
