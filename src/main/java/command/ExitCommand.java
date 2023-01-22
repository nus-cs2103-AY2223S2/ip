package command;

import duke.Ui;
import task.TaskList;

/**
 * Command to exit the chat session.
 */
public class ExitCommand extends Command {
    /**
     * Shows a goodbye greeting indicating the end of the chat session.
     * @param tasks The existing task list.
     * @param ui The ui of Duke chat.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        /* Put this line before tasks.save() because we want it to be executed
        regardless of whether the tasks could be saved to local file. */
        ui.showSuccess("ok bye");
    }

    /**
     * Determines if the current command is an exit command.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
