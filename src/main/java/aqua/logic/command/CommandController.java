package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;


/** The controller of a command. */
public abstract class CommandController {
    /**
     * Creates an {@code ExecutionService} that will not display its results.
     *
     * @param args - the argument map to work on.
     * @param manager - the LogicManager to work on.
     */
    public abstract ExecutionService getService(ArgumentMap args, LogicManager manager);


    /**
     * Creates an {@code ExecutionService} that may display its results.
     *
     * @param args - the argument map to work on.
     * @param logicManager - the {@code LogicManager} to work on.
     * @param ioManager - the {@code IoManager} to display through
     */
    public abstract ExecutionService getService(
            ArgumentMap args, LogicManager logicManager, IoManager ioManager);
}
