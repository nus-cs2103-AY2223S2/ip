package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.storage.Storage;
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

    public boolean execute(TaskList tl, Ui ui, Storage storage) {
        Task task = new Event(activity, from, to);
        tl.addTask(task);
        System.out.println("Got it. I've added this duke.task:\n" + task
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
