public class UnmarkCommand extends Command {
    private static int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        if (tasks.indexWithinRange(taskIndex)) {
            ui.showUnmark();
            tasks.markNotDone(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
    }
}
