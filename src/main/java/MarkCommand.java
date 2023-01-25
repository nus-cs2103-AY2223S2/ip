public class MarkCommand extends Command {
    private static int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.indexWithinRange(taskIndex)) {
            ui.showMark();
            tasks.markDone(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
    }
}
