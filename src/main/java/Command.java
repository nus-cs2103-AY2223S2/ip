public abstract class Command {
    private boolean isExit;

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit() {
        this.isExit = true;
    }

    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
