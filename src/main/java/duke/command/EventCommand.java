package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    public static final String EVENT_COMMAND = "event";
    private final Event event;

    public EventCommand(Event event) {
        super(EVENT_COMMAND);
        this.event = event;
    }

    @Override
    public void execute(TaskList lst, Ui ui) {
        lst.addTask(this.event);
        ui.showEvent(this.event, lst.getSize());
    }
}
