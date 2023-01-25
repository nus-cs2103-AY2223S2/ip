package duke;

public abstract class Command {
    protected abstract boolean isExit();

    protected abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException, DukeIOException;
}
