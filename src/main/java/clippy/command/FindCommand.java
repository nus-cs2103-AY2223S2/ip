package clippy.command;

import java.util.List;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `find` command.
 *
 * @author chunzkok
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Creates a FindCommand instance.
     *
     * @param query The search query used to locate tasks.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        List<Task> tasks = taskList.find(query);
        if (tasks.isEmpty()) {
            ui.prettyPrint("No tasks found!");
        } else {
            ui.prettyPrint("Here are the matching tasks in your list!");
            for (int i = 0; i < tasks.size(); i++) {
                ui.prettyPrint(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
        }
    }

}
