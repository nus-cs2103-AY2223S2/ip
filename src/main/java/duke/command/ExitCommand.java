package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents Duke's exit function.
 */
public class ExitCommand extends Command {

    /** Constructs the exit command. */
    public ExitCommand() {}

    /**
     * Begins Duke's shutdown.
     *
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) {
        return ui.produceGoodbyeOutput();
    };

    /**
     * Tells Duke to exit, shutting down.
     *
     * @return True
     */
    @Override
    public boolean canExit() {
        return true;
    }
}
