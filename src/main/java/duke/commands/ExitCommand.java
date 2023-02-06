package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Command that exits the program
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye, hope to see you again";
    }
}
