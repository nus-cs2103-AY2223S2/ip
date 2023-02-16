package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Class of UnmarkCommand that set task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int id;

    public UnmarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    /**
     * Override execute method frmm the abstract class of Command.
     *
     * @param tl       list of tasks.
     * @param storage  harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        Task task = tl.getTask(this.id - 1);
        task.setUnmark();
        String res = "Ok, I've marked this task as not done yet:\n" + task;
        return res;
    }
}
