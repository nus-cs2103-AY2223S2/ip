package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;
import iris.exception.UnknownTaskException;
import iris.task.Task;

/**
 * Marks the task at given index as not done
 */
public class UnmarkTaskCommand extends Command {
    private Task task;
    private final int index;

    public UnmarkTaskCommand(int i) {
        this.index = i;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {
        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownTaskException();
        }
        task.unmark();
        taskStore.updateTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) throws IrisException {
        return "Bummer! Have fun doing this:\n" + task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof UnmarkTaskCommand) {
            UnmarkTaskCommand c = (UnmarkTaskCommand) o;
            return this.index == c.index;
        } else {
            return false;
        }
    }
}
