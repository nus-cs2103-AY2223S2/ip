package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

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
     * @param storage Existing Storage used by the main Duke class.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "CLICK X TO SHUT DOWN. THANK YOU.";
    }
}
