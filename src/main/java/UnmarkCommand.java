public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToBeUnmarked = tasks.getTask(taskNum - 1);
        taskToBeUnmarked.markAsIncomplete();
        ui.showMarkedMsg(taskToBeUnmarked);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
