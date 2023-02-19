package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to gracefully close the application window.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Bye. Hope to see you again!";
    }

}
