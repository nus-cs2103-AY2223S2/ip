public abstract class Command {
    public abstract void execute(Storage storage, TaskList list, Ui ui) throws BookException;
    public boolean isExit() {
        return false;
    }
}
