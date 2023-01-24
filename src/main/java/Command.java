public abstract class Command {

    protected boolean isExit;
    protected boolean isSave;

    Command() {
    }
    public abstract void execute(TaskList tl, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return this.isExit;
    }

    public boolean isSave() {
        return this.isSave;
    }
}
