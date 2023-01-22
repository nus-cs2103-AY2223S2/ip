public abstract class Command {
    abstract boolean isExit();
    abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
