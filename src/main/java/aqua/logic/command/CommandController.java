package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;


/** The controller of a command. */
public abstract class CommandController {

    /**
     * Produces an ExecutionService from the given argument and manager.
     *
     * @param args - the argument map to work on.
     * @param manager - the LogicManager to work on.
     */
    public abstract ExecutionService getService(ArgumentMap args, LogicManager manager);


    public abstract ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager);


    /**
     * Return the syntax of the command.
     *
     * @returnt he syntax of the command.
     */
    public String getSyntax() {
        return "";
    }


    /**
     * Returns the description of the command.
     *
     * @return the description of the command.
     */
    public String getDescription() {
        return "";
    }
}
