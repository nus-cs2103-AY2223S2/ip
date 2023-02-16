package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Class of MarkCommand that set task as completed.
 */
public class MarkCommand extends Command {
    private int id;

    /**
     * Constructor for MarkCommand.
     *
     * @param cmd the command given by the user.
     */
    public MarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd);
    }

    /**
     * Overrides execute method from the abstract class of Command.
     *
     * @param tl       list of tasks.
     * @param storage  harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        Task task = tl.getTask(this.id - 1);
        task.setMark();
        String res = "Nice! I've marked this task as done:\n" + task;
        return res;
    }
}
