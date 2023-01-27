public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(tasks.list());
    }
}
