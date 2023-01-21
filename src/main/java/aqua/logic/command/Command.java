package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.manager.AppManager;


public enum Command {
    ;


    private final CommandDispatcherCreator creator;


    private Command(CommandDispatcherCreator creator) {
        this.creator = creator;
    }


    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return creator.createDispatcher(args, manager);
    }
}
