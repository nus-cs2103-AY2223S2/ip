package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;


/** Enumeration of commands. */
public enum Command {
    LIST(new ListCommand()),
    MARK(new MarkTaskCommand(true)),
    UNMARK(new MarkTaskCommand(false)),
    TODO(new AddToDoCommand()),
    DEADLINE(new AddDeadlineCommand()),
    EVENT(new AddEventCommand()),
    DELETE(new DeleteCommand()),
    FIND(new FilterCommand());


    private final CommandController controller;


    Command(CommandController controller) {
        this.controller = controller;
    }


    /**
     * Returns the {@code ExecutionService} of the command. {@code isLoading}
     * parameter is set to {@code false}.
     *
     * @param args - the arguments for the command to work on.
     * @param manager - the {@code LogicManager} for the command to work on.
     * @return the {@code ExecutionService} to execute.
     */
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return controller.getService(args, manager);
    }


    /**
     * Returns the {@code ExecutionService} of the command.
     *
     * @param args - the arguments for the command to work on.
     * @param manager - the {@code LogicManager} for the command to work on.
     * @param isLoading - if the command is to be executed to load data.
     * @return the {@code ExecutionService} to execute.
     */
    public ExecutionService getService(ArgumentMap args, LogicManager manager, IoManager ioManager) {
        return controller.getService(args, manager, ioManager);
    }


    /**
     * Return the syntax of the command.
     *
     * @returnt he syntax of the command.
     */
    public String getSyntax() {
        return controller.getSyntax();
    }


    /**
     * Returns the description of the command.
     *
     * @return the description of the command.
     */
    public String getDescription() {
        return controller.getDescription();
    }
}
