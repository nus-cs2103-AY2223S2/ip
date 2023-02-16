package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Class of EventCommand that set events with to and from timings.
 */
public class EventCommand extends Command {
    private String activity;
    private String from;
    private String to;

    /**
     * Constructor for EventCommand.
     *
     * @param cmd the command given by the user.
     */
    public EventCommand(String cmd) {
        String c = cmd.split(" ")[0];
        int indexOfFrom = cmd.indexOf("/from ");
        int indexOfFromTime = indexOfFrom + 6;
        int indexOfTo = cmd.indexOf("/to ");
        int indexOfToTime = indexOfTo + 4;
        this.activity = cmd.substring(c.length() + 1, indexOfFrom - 1);
        this.from = cmd.substring(indexOfFromTime, indexOfTo - 1);
        this.to = cmd.substring(indexOfToTime);
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
        Task task = new Event(activity, from, to);
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
