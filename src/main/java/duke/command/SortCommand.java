package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * SortCommand that represents command to sort task list.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    /**
     * Constructor for SortCommand.
     */
    public SortCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTaskList();
        String response = "Task list has been sorted!";
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}
