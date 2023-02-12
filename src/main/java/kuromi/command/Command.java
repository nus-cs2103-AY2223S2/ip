package kuromi.command;

import kuromi.storage.Storage;
import kuromi.view.Ui;
import kuromi.task.TaskList;

/**
 * Command meant by the user input.
 * Abstract class that will be inherited by the various command types.
 * Has an abstract method execute to execute various commands.
 */
public abstract class Command {
    /** Determines whether the program should be ended **/
    private boolean isExit = false;

    /**
     * Execute the current command.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public void setExit() {
        this.isExit = true;
    }
}
