package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke's exit function.
 */
public class ExitCommand extends Command {

    /** Constructs the exit command. */
    public ExitCommand() {}

    /**
     * Begins Duke's shutdown.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.printGoodbye();
    };

    /**
     * Tells Duke to exit, shutting down.
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
