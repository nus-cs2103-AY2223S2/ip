package duke.commands.indexedcommand;

import duke.Duke;
import duke.task.Task;

public class MarkCommand extends IndexedCommand {
    public MarkCommand() {
        super("mark");
    }

    @Override
    protected void runWithTask(Task task, final Duke instance) {
        task.setDone(true);
        output("Marked this as done!\n%s\n", task.toString());
    }
}