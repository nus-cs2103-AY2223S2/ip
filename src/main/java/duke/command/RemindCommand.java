package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'remind' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class RemindCommand extends Command {
    public RemindCommand(String fullCommand) {
        super(fullCommand);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.getTasksToRemind();
        String returnDialog = ui.showRemindMessage();
        returnDialog += ui.showTaskListPretty(foundTasks);
        return returnDialog;
    }
    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>false</code>
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
