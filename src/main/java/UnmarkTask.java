public class UnmarkTask implements Command {
    private int index;

    public UnmarkTask(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.unmark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        ui.showUnmarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
