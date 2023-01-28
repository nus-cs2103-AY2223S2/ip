abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
