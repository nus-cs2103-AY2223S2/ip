package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Class of DeleteCommand that remove tasks.
 */
public class DeleteCommand extends Command {
    private int id;

    /**
     * Constructor for DeleteCommand.
     *
     * @param cmd the command given by the user.
     */
    public DeleteCommand(String cmd) {
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
        Task task = tl.removeTask(this.id - 1);
        String res = "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tl.getSize() + " tasks in the list.";
        return res;
    }
}
