package duke.command;

import duke.database.DukeRepo;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * An abstract class that defines the interface for Duke commands.
 */
public abstract class Command {

    /**
     * Performs the command action with the data and ui layer.
     *
     * @param db {@link DukeRepo} object
     * @param ui {@link Ui} object
     * @throws DukeException
     */
    public abstract void execute(DukeRepo db, Ui ui) throws DukeException;

    /**
     * indicator for for exit indicator.
     *
     * @return boolean
     */
    public abstract boolean isExit();
}
