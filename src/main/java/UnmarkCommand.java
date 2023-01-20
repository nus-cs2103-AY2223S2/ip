public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) throws DukeException {
        Task task = taskStore.unmarkTask(index);
        ui.showUnmarkTask(task);
        storage.save(taskStore.createTaskListString());
    }
}
