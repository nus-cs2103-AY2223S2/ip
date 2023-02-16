package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;

/**
 * removes all tasks from both hard drive and current task list
 */
public class ResetCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {
        tasks.clear();
        taskStore.reset();
        assert tasks.size() == 0 : "Size of tasks should be 0 after resetting";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) throws IrisException {
        return "Your task list has been resetted. You have no tasks.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ResetCommand;
    }
}
