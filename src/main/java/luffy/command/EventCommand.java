package luffy.command;

import luffy.storage.TaskList;
import luffy.ui.Ui;
import luffy.task.Event;

/**
 * The EventCommand class encapsulates the variables and methods related to Event commands.
 */
public class EventCommand extends Command {
    private static final String EVENT_COMMAND = "event";
    private final Event event;

    /**
     * Constructor creates an instance of EventCommand.
     * @param event An instance of Event.
     */
    public EventCommand(Event event) {
        super(EVENT_COMMAND);
        this.event = event;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.event);
        return ui.showEvent(this.event, taskList.getSize());
    }
}
