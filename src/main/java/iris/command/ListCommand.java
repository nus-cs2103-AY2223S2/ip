package iris.command;

import iris.TaskList;
import iris.TaskStore;

/**
 * lists all tasks, events and deadlines
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        if (tasks.size() == 0) {
            return "You have no tasks.";
        }
        String startingStr = tasks.size() < 10
                ? "(So few~ good going!)\n"
                : "(So many >:O)\n";
        String str = "You have the following tasks: " + startingStr;
        return str + tasks + "You have " + tasks.size() + " tasks.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
