package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/** A representation of the "event" command in Duke. */
public class EventCommand extends Command {

    private final String NAME = "event";
    private String title;
    private String from;
    private String to;

    /**
     * Initializes an event command with a given title, start date, and end date.
     * 
     * @param title The title of the Event to be created
     * @param from  The start date of the Event to be created
     * @param to    The end date of the Event to be created
     */
    public EventCommand(String title, String from, String to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task task = new Event(this.title, this.from, this.to);
        taskList.addTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        EventCommand cmd = (EventCommand) obj;
        return this.title.equals(cmd.title) && this.from.equals(cmd.from) && this.to.equals(cmd.to);
    }
}
