package elise.commands;

import elise.EliseException;
import elise.internal.Storage;
import elise.internal.TaskList;
import elise.internal.Ui;

import java.io.IOException;

/**
 * Abstraction of a command to be executed.
 * Stores all information necessary for execution.
 */
public interface Command {

    /**
     * Executes command.
     *
     * @param ui Ui object of Elise.
     * @param taskList TaskList object of Elise.
     * @param storage Storage object of Elise.
     * @throws EliseException EliseException unique to Elise
     * @throws IOException Unexpected IOException
     */
    String execute(Ui ui, TaskList taskList, Storage storage) throws EliseException, IOException;
}
