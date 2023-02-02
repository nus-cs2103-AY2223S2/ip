package aqua.logic;

import aqua.logic.command.Command;
import aqua.manager.LogicManager;


/**
 * Represents the user's command line input that create ExecutionServices. An
 * association class of a {@code Command} and its corresponding arguments.
 */
public class CommandLineInput {
    private final Command command;
    private final ArgumentMap args;


    /**
     * Constructs a CommandLineInput.
     *
     * @param command - the command.
     * @param args - the argument map.
     */
    public CommandLineInput(Command command, ArgumentMap args) {
        this.command = command;
        this.args = args;
    }


    /**
     * Returns the ExecutionService to execute the command.
     * {@code isLoading} is set to {@code false}.
     *
     * @param manager - the LogicManager for the command to work on.
     * @return the ExecutionService of the command.
     */
    public ExecutionService getService(LogicManager manager) {
        return command.getService(args, manager);
    }


    /**
     * Returns the ExecutionService to execute the command.
     *
     * @param manager - the LogicManager for the command to work on.
     * @param isLoading - if the command purpose is to load a previous state.
     * @return the ExecutionService of the command.
     */
    public ExecutionService getService(LogicManager manager, boolean isLoading) {
        return command.getService(args, manager, isLoading);
    }
}
