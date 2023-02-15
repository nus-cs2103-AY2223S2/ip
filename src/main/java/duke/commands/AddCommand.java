package duke.commands;

/**
 * This is a command to add a task to Duke.
 */
public abstract class AddCommand extends Command {
    private final String commandType;

    /**
     * Constructor of the class AddCommand.
     *
     * @param commandType the type of AddCommand.
     */
    public AddCommand(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Returns whether the program Duke should terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
