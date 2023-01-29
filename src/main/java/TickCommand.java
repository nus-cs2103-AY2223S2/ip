public class TickCommand extends Command{
    private int taskIndex;

    public TickCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.tickTask(this.taskIndex);
        ui.printTickTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
