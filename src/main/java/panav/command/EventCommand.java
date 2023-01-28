package panav.command;

import panav.storage.Storage;
import panav.task.Event;
import panav.task.Task;
import panav.task.TaskList;
import panav.ui.Ui;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private String eventMessage;
    private String from;
    private String to;
    public EventCommand(String eventMessage, String from, String to) {
        this.eventMessage = eventMessage;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = new Event(eventMessage, from, to);
        tasks.addTask(curr);
        ui.showAddTaskMessage(tasks, curr);
    }

    @Override
    public String toString() {
        return EventCommand.COMMAND_WORD;
    }

}
