public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.markTaskDone(index);
        storage.save(tasks);
        ui.showMarkTask(markedTask);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
