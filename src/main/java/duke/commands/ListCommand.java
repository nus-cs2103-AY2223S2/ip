package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The duke.commands.ListCommand class implements the action of listing all saved tasks.
 *
 * @author Chia Jeremy
 */

public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            String s = (i + 1) + ". " + tasks.getTask(i) + "\n";
            sb.append(s);
        }
        ui.display(sb.toString());
    }
}
