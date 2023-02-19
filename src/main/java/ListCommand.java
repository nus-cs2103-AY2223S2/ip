public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.ShowList(tasks.getAllTasks());
    }
}