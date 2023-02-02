package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents Duke's Echo function.
 */
public class EchoCommand extends Command {
    /** Constructs the echo command. */
    public EchoCommand() {}

    /**
     * Echoes the user input.
     *
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) {
        return ui.produceInputAsOutput();
    };

}
