package duke.command;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import duke.constant.DialogType;
import duke.database.DukeRepo;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * An abstract class that defines the interface for Duke commands.
 */
public abstract class Command {

    /**
     * Executes the command action with the data output it to ui layer.
     *
     * @param db {@link DukeRepo} object
     * @param ui {@link Ui} object
     * @throws DukeException
     */
    public abstract void execute(DukeRepo db, Ui ui) throws DukeException;

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
