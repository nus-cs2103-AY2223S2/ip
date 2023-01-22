public class UnmarkCommand extends Command {
    private int taskNo;

    UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(taskNo).unmarkAsDone();
        ui.showUnmarkDone(tasks, taskNo);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}