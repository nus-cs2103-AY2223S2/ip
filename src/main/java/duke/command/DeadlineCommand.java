package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Class of DeadlineCommand that set deadline for tasks.
 */
public class DeadlineCommand extends Command {
    private String activity;
    private String date;
    private String time;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param cmd the command given by the user.
     */
    public DeadlineCommand(String cmd) {
        String c = cmd.split(" ")[0];
        int indexOfBy = cmd.indexOf("/by ");
        int indexOfDate = indexOfBy + 4; // "/by "
        this.activity = cmd.substring(c.length() + 1, indexOfBy - 1);
        String datetime = cmd.substring(indexOfDate);
        this.date = datetime.split(" ")[0];
        this.time = datetime.split(" ")[1];
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
        Task task = new Deadline(this.activity, this.date, this.time);
        tl.addTask(task);
        System.out.println("Got it. I've added this duke.task:\n" + task
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
