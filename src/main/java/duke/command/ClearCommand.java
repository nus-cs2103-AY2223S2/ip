package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command: Clears the task list
 */
public class ClearCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clear();
        ui.showClear();
        storage.save(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     */
    public String executeString(TaskList tasks, Ui ui, Storage storage) {
        tasks.clear();
        storage.save(tasks);
        return ui.stringClear();
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
