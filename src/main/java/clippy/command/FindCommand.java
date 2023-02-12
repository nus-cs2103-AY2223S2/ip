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
        List<Task> matches = taskList.find(query);
        if (matches.isEmpty()) {
            ui.prettyPrint("No tasks found!");
        } else {
            ui.prettyPrint("Here are the matching tasks in your list!");
            printMatchingTasks(matches, ui);
        }
    }

    /**
     * Prints all the matching tasks passed into the method.
     *
     * @param matches A list containing the matching tasks.
     * @param ui The UI instance of the current program.
     */
    public void printMatchingTasks(List<Task> matches, Ui ui) {
        for (int i = 0; i < matches.size(); i++) {
            ui.prettyPrint(String.format("%d. %s", i + 1, matches.get(i).toString()));
        }
    }

}
