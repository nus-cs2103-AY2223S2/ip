package duke.commands;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to print all task in a task list to the console.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("LIST");
    }

    @Override
    public String execute(TaskList tasks) {
        String reply = "Here are the tasks in your list:\n";
        Ui.displayMessage(reply);
        reply += tasks.list();
        return reply;
    }
}
