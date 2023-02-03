package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


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
     * Override execute method from the abstract class of Command.
     *
     * @param tl       - list of tasks.
     * @param ui       - interface.
     * @param storage  - harddisk store using textfile.
     * @return String  - returns the result of the command execution.
     */
    public String execute(TaskList tl, Ui ui, Storage storage) {
        Task task = new Event(activity, from, to);
        tl.addTask(task);
        return "Got it. I've added this duke.task:\n" + task
                + "\n Now you have " + tl.getSize() + " tasks in the list.";
    }
}
