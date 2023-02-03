package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to add an event to a task list.
 */
public class EventCommand extends Command{
    private String requestContent;
    public EventCommand(String requestContent){
        super("EVENT");
        this.requestContent = requestContent;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(requestContent);
    }
}
