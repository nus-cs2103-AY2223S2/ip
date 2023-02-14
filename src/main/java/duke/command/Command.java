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
     * @param db {@link DukeRepo} a data layer interface object
     * @param con a handler function for execution result
     * @throws DukeException general duke exception.
     * @see BiConsumer
     */
    public abstract void execute(DukeRepo db, BiConsumer<DialogType, String> con) throws DukeException;

    /**
     * Represents an indicator for exit event.
     *
     * @return an exit indicator
     */
    public abstract boolean isExit();
}
