public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) throws DukeException {
        Task task = taskStore.markTaskDone(index);
        ui.showMarkTask(task);
        storage.save(taskStore.createTaskListString());
    }
}
