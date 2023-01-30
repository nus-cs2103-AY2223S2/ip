abstract public class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);
    abstract public boolean isExit();
}
