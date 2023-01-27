public abstract class Command {
    public boolean isEnd() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException;
}
