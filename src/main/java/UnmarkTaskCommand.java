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
        taskStore.updateFile(tasks.toString());
        Ui.output("Bummer! Have fun doing this:");
        Ui.output(task.toString());
    }
}
