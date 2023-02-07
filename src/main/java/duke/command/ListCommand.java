package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * ListCommand.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = tasks.listTasks();
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }

}
