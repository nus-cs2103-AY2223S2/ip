package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;

/**
 * Command: Clears the task list
 */
public class ClearCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     */
    public void execute(TaskList tasks, Storage storage) {
        tasks.clear();
        Ui.printer(Views.CLEAR_LIST_STRING);
        storage.save(tasks);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     */
    public String executeString(TaskList tasks, Storage storage) {
        tasks.clear();
        storage.save(tasks);
        return Views.CLEAR_LIST_STRING.str();
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
