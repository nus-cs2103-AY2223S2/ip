package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
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
    FIND(new FilterCommand()),
    BYE(new ByeCommand());


    private final CommandController controller;


    Command(CommandController controller) {
        this.controller = controller;
    }


    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return controller.getService(args, manager, false);
    }


    public ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading) {
        return controller.getService(args, manager, isLoading);
    }


    public String getSyntax() {
        return controller.getSyntax();
    }


    public String getDescription() {
        return controller.getDescription();
    }
}
