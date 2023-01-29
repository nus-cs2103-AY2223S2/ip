public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
