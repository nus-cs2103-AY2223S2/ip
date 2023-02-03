package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to add a deadline.
 */
public class DeadlineCommand extends Command{
    private String requestContent;
    public DeadlineCommand(String requestContent){
        super("DEADLINE");
        this.requestContent = requestContent;
    }

    /**
     * Adds deadline to a task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(requestContent);
    }
}
