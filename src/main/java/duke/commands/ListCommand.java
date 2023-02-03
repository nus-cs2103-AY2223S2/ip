package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to print all task in a task list to the console.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("LIST");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage("  Here are the tasks in your list:\n");
        tasks.list();
    }
}
