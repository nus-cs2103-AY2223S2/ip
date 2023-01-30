package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class implements the action of listing all saved tasks.
 *
 * @author Chia Jeremy
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            String s = (i + 1) + ". " + tasks.getTask(i) + "\n";
            sb.append(s);
        }
        ui.setResponse(sb.toString());
    }
}
