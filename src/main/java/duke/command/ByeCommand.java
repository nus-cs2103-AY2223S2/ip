package duke.command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Prints out goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printBye();
    }

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return True indicating that the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}