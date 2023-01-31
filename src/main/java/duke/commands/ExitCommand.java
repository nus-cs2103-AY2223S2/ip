package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a Command to exit from Duke.
 * */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exit command does not execute anything and Duke will close after exit command.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param ui Existing Ui used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
