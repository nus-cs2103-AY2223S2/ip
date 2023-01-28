package iris.command;

import iris.exception.UnknownTaskException;
import iris.TaskList;
import iris.Ui;
import iris.exception.IrisException;
import iris.TaskStore;
import iris.task.Task;

public class UnmarkTaskCommand extends Command {
    int index;

    public UnmarkTaskCommand(int i) {
        this.index = i;
    }

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
