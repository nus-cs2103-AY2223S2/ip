public abstract class Command {
    protected String input;
    protected boolean isExit = false;

    public Command(String input) {
        this.input = input;
    }
    public abstract void execute(TaskList storage, Ui ui);
}
