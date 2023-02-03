package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


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
     * Override execute method from the abstract class of Command.
     *
     * @param tl       - list of tasks.
     * @param ui       - interface.
     * @param storage  - harddisk store using textfile.
     * @return boolean - returns true.
     */
    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        Task task = tl.removeTask(this.id - 1);
        System.out.println("Noted. I've removed this duke.task:\n" + task
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
