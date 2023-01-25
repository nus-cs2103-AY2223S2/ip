public abstract class Command {
    private final String commandName;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public abstract void execute(TaskList lst, Ui ui) throws DukeException;

    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
