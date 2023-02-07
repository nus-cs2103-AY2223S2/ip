package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * UnknownCommand.
 */
public class UnknownCommand extends Command {
    private final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    /**
     * Constructor for UnknownCommand.
     */
    public UnknownCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showResponse(ERROR_MESSAGE);
        this.responseFromDukeAfterExecution = ERROR_MESSAGE;
    }
}
