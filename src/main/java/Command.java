abstract class Command {
    boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
