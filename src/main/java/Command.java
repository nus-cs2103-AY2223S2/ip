import Tasks.Task;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList l, Ui ui, Storage s);
}
