package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;
import iris.exception.IrisException;
import iris.exception.UnknownTaskException;
import iris.task.Task;

/**
 * Marks the task at given index as not done
 */
public class UnmarkTaskCommand extends Command {
    private final int index;

    public UnmarkTaskCommand(int i) {
        this.index = i;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        Task task;
        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownTaskException();
        }
        task.unmark();
        taskStore.updateTasks(tasks);
        Ui.output("Bummer! Have fun doing this:");
        Ui.output(task.toString());
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
