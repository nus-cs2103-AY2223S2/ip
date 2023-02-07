package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * TerminateCommand.
 */
public class TerminateCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor for TerminateCommand.
     */
    public TerminateCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks.getArrayList());
        ui.showGoodBye();
        this.responseFromDukeAfterExecution = "Bye. Hope to see you again soon!";
    }

    public boolean isExit() {
        return true;
    }
}
