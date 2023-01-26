package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Got it. I've added this task:\n";
        msg += tasks.toString() + "\n";
        msg += "Now you have " + tasks.size() + " tasks in the list.";
        ui.show(msg);
    }
}
