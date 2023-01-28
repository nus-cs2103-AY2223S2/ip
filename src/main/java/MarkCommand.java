public class MarkCommand extends Command {
    private int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToBeMarked = tasks.getTask(taskNum - 1);
        taskToBeMarked.markAsDone();
        ui.showMarkedMsg(taskToBeMarked);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
