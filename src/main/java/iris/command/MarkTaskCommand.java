package src.main.java.command;

public class MarkTaskCommand extends Command {
    int index;

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
}
