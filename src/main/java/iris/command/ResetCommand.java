package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;
import iris.Ui;

/**
 * removes all tasks from both hard drive and current task list
 */
public class ResetCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        tasks.clear();
        taskStore.reset();
        Ui.output("Your task list has been resetted. You have no commands.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ResetCommand;
    }
}
