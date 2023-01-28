package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Command: Creates a command to exit the program
 */
public class ExitCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
        storage.save(tasks);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return true;
    }
}