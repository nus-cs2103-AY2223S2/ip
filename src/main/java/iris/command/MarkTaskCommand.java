package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;
import iris.exception.IrisException;
import iris.exception.UnknownTaskException;
import iris.task.Task;

public class MarkTaskCommand extends Command {
    private final int index;

    public MarkTaskCommand(int i) {
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
        task.mark();
        taskStore.updateTasks(tasks);
        Ui.output("Good job! I've marked this task done:");
        Ui.output(task.toString());
    }

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
