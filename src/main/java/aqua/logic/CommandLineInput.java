package aqua.logic;

import aqua.logic.command.Command;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;


/** Represents the user's command line input. */
public class CommandLineInput {
    private final Command command;
    private final ArgumentMap args;


    /**
     * Constructs a {@code CommandLineInput}.
     *
     * @param command - the command.
     * @param args - the argument map.
     */
    public CommandLineInput(Command command, ArgumentMap args) {
        this.command = command;
        this.args = args;
    }


    /**
     * Returns the {@code ExecutionService} to execute the command. The service
     * returned does not display its results.
     *
     * @param manager - the {@code LogicManager} for the command to work on.
     * @return the {@code ExecutionService} of the command.
     */
    public ExecutionService getService(LogicManager manager) {
        return command.getService(args, manager);
    }


    /**
     * Returns the {@code ExecutionService} to execute the command. The service
     * returned may display its results.
     *
     * @param logicManager - the {@code LogicManager} for the command to work
     *      on.
     * @param ioManager - the {@code IoManager} to display through.
     * @return the {@code ExecutionService} of the command.
     */
    public ExecutionService getService(LogicManager logicManager, IoManager ioManager) {
        return command.getService(args, logicManager, ioManager);
    }
}
