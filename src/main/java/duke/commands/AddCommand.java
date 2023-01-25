package duke.commands;

public abstract class AddCommand extends Command {
    private final String commandType;

    public AddCommand(String commandType) {
        this.commandType = commandType;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
