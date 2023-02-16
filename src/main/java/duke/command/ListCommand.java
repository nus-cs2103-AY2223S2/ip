package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Class of ListCommand that shows all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Overrides execute method from the abstract class of Command.
     *
     * @param tl      list of tasks.
     * @param storage harddisk store using textfile.
     * @return String returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        String res = tl.getTasksString();
        if (res == "") {
            res = "Your list is empty!";
        }
        return res;
    }
}
