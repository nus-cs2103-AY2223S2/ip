package duke.command;

import java.util.function.BiConsumer;

import duke.constant.DialogType;
import duke.database.DukeRepo;
import duke.exception.DukeException;

/**
 * An abstract class that defines the interface for Duke commands.
 */
public abstract class Command {

    /**
     * Executes the command action with the data and return the result to ui layer.
     *
     * @param db {@link DukeRepo} object
     * @param con {@link BiConsumer} object
     * @throws DukeException
     */
    public abstract void execute(DukeRepo db, BiConsumer<DialogType, String> con) throws DukeException;

    /**
     * indicator for for exit indicator.
     *
     * @return boolean
     */
    public abstract boolean isExit();
}
