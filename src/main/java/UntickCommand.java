public class UntickCommand extends Command{
    private int taskIndex;

    public UntickCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.untickTask(this.taskIndex);
        ui.printUntickTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
