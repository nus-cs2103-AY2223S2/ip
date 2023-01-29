public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(this.taskIndex);
        ui.printDeleteTask(deletedTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
