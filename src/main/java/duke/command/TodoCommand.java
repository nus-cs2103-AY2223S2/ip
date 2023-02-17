package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Class of TodoCommand that add task to the list.
 */
public class TodoCommand extends Command {
    private String activity;

    /**
     * Constructor for TodoCommand.
     *
     * @param cmd the command given by the user.
     */
    public TodoCommand(String cmd) {
        String c = cmd.split(" ")[0];
        this.activity = cmd.substring(c.length() + 1);
    }

    /**
     * Overrides execute method from the abstract class of Command
     *
     * @param tl       list of tasks.
     * @param storage  harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        String res = "";
        Task task = new Todo(this.activity);
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
