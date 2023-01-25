package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

<<<<<<< .merge_file_XEP54C
/**
 * Implementation of Bye Command
 **/
=======
>>>>>>> .merge_file_nbqJ2J
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