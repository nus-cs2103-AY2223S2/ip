package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;


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
     * Overrides execute method from the abstract class of Command.
     *
     * @param tl      list of tasks.
     * @param storage harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        String res = "";
        Task task = new Deadline(this.activity, this.date, this.time);
        if (tl.isTaskDuplicate(task)) {
            res = "Task has already been added to the task list!";
        } else {
            tl.addTask(task);
            res = "Got it. I've added this task:\n" + task
                    + "\nNow you have " + tl.getSize() + " tasks in the list.";
        }
        return res;
    }
}
