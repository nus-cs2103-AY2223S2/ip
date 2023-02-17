package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ErrorCommand that represents command that shows errors in the program.
 */
public class ErrorCommand extends Command {
    private String errorResponse;
    /**
     * Constructor for ErrorCommand.
     */
    public ErrorCommand(String errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.responseFromDukeAfterExecution = errorResponse;
    }
}
