public class DeleteCommand extends Command {
    private static int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        if (tasks.indexWithinRange(taskIndex)) {
            ui.showDelete();
            tasks.deleteTask(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
    }
}
