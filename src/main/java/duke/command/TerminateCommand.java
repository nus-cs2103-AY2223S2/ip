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

    /**
     * Executes saving of current state of task list and terminates Duke.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks.getArrayList());
        ui.showGoodBye();
    }

    public boolean isExit() {
        return true;
    }
}
