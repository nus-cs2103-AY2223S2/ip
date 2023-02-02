package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Class of ListCommand that shows all the tasks.
 */
public class ListCommand extends Command {

    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        System.out.println(tl.getTasksString());
        return true;
    }
}
