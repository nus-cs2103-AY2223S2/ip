public abstract class Command {
    private final String fullCommand;
    public Command(String command) {
        fullCommand = command;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
