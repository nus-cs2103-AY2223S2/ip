package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;
import iris.exception.UnknownTaskException;
import iris.task.Task;

/**
 * marks a task as done
 */
public class MarkTaskCommand extends Command {
    private Task task;
    private final int index;

    public MarkTaskCommand(int i) {
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
        task.mark();
        taskStore.updateTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) throws IrisException {
        return "Good job! I've marked this task done:\n" + task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MarkTaskCommand) {
            MarkTaskCommand c = (MarkTaskCommand) o;
            return this.index == c.index;
        } else {
            return false;
        }
    }
}
