package iris.command;

import iris.TaskList;
import iris.Ui;
import iris.TaskStore;

/**
 * lists all tasks, events and deadlines
 */
public class ListCommand extends Command{
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        if (tasks.size() == 0) {
            Ui.output("You have no tasks.");
        }

        String str = tasks.size() < 10
                ? "(So few~ good going!)\n"
                : "(So many >:O)\n";
        str = String.join(" ", "You have the following tasks:", str);

        Ui.output(str + tasks + "You have " + tasks.size() + " tasks.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
