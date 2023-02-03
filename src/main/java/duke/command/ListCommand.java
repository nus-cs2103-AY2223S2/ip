package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Class of ListCommand that shows all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Override execute method from the abstract class of Command.
     *
     * @param tl       - list of tasks.
     * @param ui       - interface.
     * @param storage  - harddisk store using textfile.
     * @return boolean - returns true.
     */
    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        System.out.println(tl.getTasksString());
        return true;
    }
}
