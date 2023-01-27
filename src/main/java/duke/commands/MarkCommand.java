package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The duke.commands.MarkCommand class implements the action of marking tasks.
 *
 * @author Chia Jeremy
 */

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.mark(this.index);
        storage.mark(this.index);
        ui.display("Nice! I've marked this task as done:\n" + tasks.getTask(this.index));
    }
}
