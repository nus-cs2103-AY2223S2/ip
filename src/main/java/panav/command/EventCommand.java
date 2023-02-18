package panav.command;

import panav.storage.Storage;
import panav.task.Event;
import panav.task.Task;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Class to encapsulate a 'event' command, extends from Command.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private String eventMessage;
    private String from;
    private String to;

    /**
     * Constructor to initialise attributes.
     *
     * @param eventMessage event description.
     * @param from start time.
     * @param to end time.
     */
    public EventCommand(String eventMessage, String from, String to) {
        this.eventMessage = eventMessage;
        this.from = from;
        this.to = to;
    }

    /**
     * Specifies the behaviour of 'event' command when called to execute. Creates a new event
     * with the provided information and adds it to the list of tasks.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = new Event(eventMessage, from, to);
        tasks.addTask(curr);
        return ui.showAddTaskMessage(tasks, curr);
    }

    @Override
    public String toString() {
        return EventCommand.COMMAND_WORD;
    }

}
