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
        System.out.println(cmd);
        String c = cmd.split(" ")[0];
        this.activity = cmd.substring(c.length() + 1);
    }

    /**
     * Override execute method from the abstract class of Command
     *
     * @param tl       list of tasks.
     * @param storage  harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        Task task = new Todo(this.activity);
        tl.addTask(task);
        return "Got it. I've added this duke.task:\n" + task
                + "\n Now you have " + tl.getSize() + " tasks in the list.";
    }

}
