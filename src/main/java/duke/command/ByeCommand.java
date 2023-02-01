package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implementation of Bye Command
 **/
public class ByeCommand extends Command {

    public String execute(TaskList tasks, Ui ui, StorageList storage) {
        return "See you soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}