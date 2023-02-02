package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.manager.LogicManager;


/** A provider of ExecutionService when given an ArgumentMap and LogicManager. */
public abstract class CommandController {
    /**
     * Produces an ExecutionService from the given argument and manager.
     * {@code isLoading} parameter is set to {@code false}.
     *
     * @param args - the argument map to work on.
     * @param manager - the LogicManager to work on.
     * @return an ExecutionService to execute the command.
     */
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return getService(args, manager, false);
    }

    /**
     * Produces an ExecutionService from the given argument and manager.
     *
     * @param args - the argument map to work on.
     * @param manager - the LogicManager to work on.
     * @param isLoading - if the command purpose is to load a previous state.
     * @return an ExecutionService to execute the command.
     */
    public abstract ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading);


    /**
     * Returns the syntax of the command.
     *
     * @return the syntax of the command.
     */
    public String getSyntax() {
        return "";
    }


    /**
     * Returns teh description of what the command does.
     *
     * @return the description of what the command does.
     */
    public String getDescription() {
        return "";
    }
}
