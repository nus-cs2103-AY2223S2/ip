package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implementation of Bye Command
 **/
public class ByeCommand extends Command {

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        System.out.println("See you soon!");
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}