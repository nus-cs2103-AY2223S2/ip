package dukes.command;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;

import dukes.task.Task;

import java.util.List;

/**
 * Subclass of Command that handles the command to find task based on pattern.
 */
public class FindCommand extends Command {

    /**
     * Constructor of FindCommand class.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body the search pattern to be matched.
     */
    public FindCommand(boolean isExit, boolean isValid,
                      String header, String body) {
        super(isExit, isValid, header, body);
        // here body = search pattern
    }

    /**
     * Print the tasks in the list that matches the specific pattern.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if unexpected runtime issue occurs.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> findList = tasks.searchTaskList(this.body);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < findList.size(); i++) {
            sb.append(i+1).append(". ");
            sb.append(findList.get(i).toString()).append("\n");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        ui.showFind(sb.toString());
    }

    public String runCommand(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> findList = tasks.searchTaskList(this.body);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < findList.size(); i++) {
            sb.append(i+1).append(". ");
            sb.append(findList.get(i).toString()).append("\n");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return ui.returnFind(sb.toString());
    }
}
