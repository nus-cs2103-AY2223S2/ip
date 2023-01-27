package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Command meant by the user input.
 * Abstract class that will be inherited by the various command types.
 * Has an abstract method execute to execute various commands.
 */
public abstract class Command {
    /** Determines whether the program should be ended **/
    boolean isExit = false;

    /**
     * Execute the current command.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether the program should be terminated.
     *
     * @return Boolean value if true then exit, if false then not exit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
