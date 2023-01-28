package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.manager.LogicManager;


/**
 * Represents a command that accepts an ArgumentMap and an AppManager to
 * produce an ExecutionDispatcher that will execute the command.
 */
public abstract class Command {
    /**
     * Produces an ExecutionDispater from the given argument and manager.
     * 
     * @param args - the argument map.
     * @param manager - the application AppManager.
     * @return an ExecutionDispatcher to execute the command.
     */
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return getService(args, manager, false);
    }

    public abstract ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading);
}
