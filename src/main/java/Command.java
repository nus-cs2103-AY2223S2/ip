public abstract class Command {

    protected boolean isExit;

    Command() {
    }
    public abstract void execute(TaskList tl, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
