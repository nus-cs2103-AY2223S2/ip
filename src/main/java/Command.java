public abstract class Command {
    public boolean shouldContinue() {
        return true;
    }
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);
}
