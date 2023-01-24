public class MarkTask implements Command {
    private int index;

    public MarkTask(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.mark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        ui.showMarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
