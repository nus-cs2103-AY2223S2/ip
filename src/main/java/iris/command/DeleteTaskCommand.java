package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;
import iris.exception.UnknownTaskException;
import iris.task.Task;

/**
 * Deletes task at given index
 */
public class DeleteTaskCommand extends Command {
    private Task task;
    private final int i;
    public DeleteTaskCommand(int i) {
        this.i = i;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {
        int temp = tasks.size();
        try {
            task = tasks.get(i);
            tasks.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownTaskException();
        }
        assert tasks.size() == temp - 1 : "The size of task list should decrease by one";
        taskStore.updateTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        return "I've removed this task \n" + task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteTaskCommand) {
            DeleteTaskCommand dtc = (DeleteTaskCommand) o;
            return this.i == dtc.i;
        } else {
            return false;
        }
    }
}
