package src.main.java.command;

public class DeleteTaskCommand extends Command {
    int i;
    public DeleteTaskCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        Task task;
        try {
            task = tasks.get(i);
            tasks.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownTaskException();
        }
        taskStore.updateTasks(tasks);
        Ui.output("I've removed this task");
        Ui.output(task.toString());
    }
}
