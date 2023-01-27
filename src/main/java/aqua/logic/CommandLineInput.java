package aqua.logic;

import aqua.logic.command.Command;
import aqua.manager.LogicManager;


/** 
 * Represents the user's command line input that produces ExecutionDispatchers
 * when given an AppManager.
 */
public class CommandLineInput {
    private final Command command;
    private final ArgumentMap args;


    public CommandLineInput(Command command, ArgumentMap args) {
        this.command = command;
        this.args = args;
    }


    /**
     * Returns the ExecutionDispatcher to execute the command.
     * 
     * @param manager - the AppManager for the command to wrok on.
     * @returnt he ExecutionDispatcher of the command.
     */
    public ExecutionService getService(LogicManager manager) {
        return command.getService(args, manager);
    }


    public ExecutionService getService(LogicManager manager, boolean isLoading) {
        return command.getService(args, manager, isLoading);
    }
}
