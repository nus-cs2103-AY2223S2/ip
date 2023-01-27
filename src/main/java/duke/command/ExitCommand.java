package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Command to exit the current session.
 */
public class ExitCommand extends Command {
    CommandType command;

    /**
     * Main constructor.
     */
    public ExitCommand() {
        this.command = CommandType.bye;
    }

    /**
     * Exit the current session.
     * Ask UI to print output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Bye. Hope to see you again soon!";
        ui.show(msg);
        super.isExit = true;
    }
}
