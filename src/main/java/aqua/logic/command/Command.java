package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.manager.AppManager;


public enum Command {
    LIST(new ListCommand()),
    TODO(new AddToDoCommand()),
    DEADLINE(new AddDeadlineCommand()),
    EVENT(new AddEventCommand()),
    DELETE(new DeleteCommand()),
    BYE(new ByeCommand());


    private final CommandDispatcherCreator creator;


    private Command(CommandDispatcherCreator creator) {
        this.creator = creator;
    }


    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return creator.createDispatcher(args, manager);
    }
}
