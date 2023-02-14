package duke.commands.indexedcommand;

import duke.Duke;
import duke.task.Task;

public class DeleteCommand extends IndexedCommand {
    public DeleteCommand() {
        super("delete");
    }

    @Override
    protected void runWithTask(Task task, final Duke instance) {
        instance.getTaskList().remove(task);
        output("Removed this from your task list:\n%s", task.toString());
    }
}
