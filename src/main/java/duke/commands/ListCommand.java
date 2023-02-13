package duke.commands;

import java.util.List;

import duke.Duke;
import duke.Utils;
import duke.parser.Arguments;
import duke.task.Task;

/**
 * Command to print the current list of tasks
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    @Override
    protected void executeInternal(Arguments args, Duke instance) {
        List<Task> tasks = instance.getTaskList();

        if (tasks.size() == 0) {
            output("No stored tasks!");
        } else {
            output("You have a total of %d tasks:\n%s", 
                tasks.size(),
                Utils.flattenIterableWithIndex(tasks, 1)
            );
        }
    }
}
