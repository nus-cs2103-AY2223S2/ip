public abstract class Command {
    String cmd;
    String description;

    protected abstract boolean isExit();

    protected abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException, DukeIOException;
}
