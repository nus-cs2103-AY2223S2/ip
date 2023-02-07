package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnknownCommand that represents a command that Duke does not recognise.
 */
public class UnknownCommand extends Command {
    private static final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
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
