package command;

import java.util.ArrayList;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command refreshing the UI with the contents of a <code>TaskList</code>.
 */
public class RefreshTasks implements Command {
    /**
     * Creates a refresh-tasks command.
     */
    public RefreshTasks() {

    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> outLines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            outLines.add((i + 1) + ". " + tasks.get(i));
        }
        ui.refreshTasks(outLines.toArray(new String[outLines.size()]));
    }
}
