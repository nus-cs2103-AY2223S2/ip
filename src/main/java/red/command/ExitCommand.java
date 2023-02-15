package red.command;

import red.storage.Storage;

import red.task.TaskList;

import red.ui.UI;

/**
 * This class specifies the act of exiting the program.
 */

public class ExitCommand extends Command {
    /**
     * Displays the goodbye message and terminate the program.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        storage.saveToStorage();
        ui.addCurrentReply("Goodbye");
        System.out.println("Goodbye");
    }

    /**
     * Indicates that this Command is the exit Command.
     *
     * @return returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
