package duke.commands.indexedcommand;

import duke.Duke;
import duke.task.Task;

public class UnmarkCommand extends IndexedCommand {
    public UnmarkCommand() {
        super("unmark");
    }

    @Override
    protected void runWithTask(Task task, final Duke instance) {
        task.setDone(false);
        output("Marked this as not done!\n%s\n", task.toString());
    }
}