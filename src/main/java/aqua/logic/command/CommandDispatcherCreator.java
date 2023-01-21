package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.manager.AppManager;


public interface CommandDispatcherCreator {
    public ExecutionDispatcher createDispatcher(ArgumentMap args, AppManager manager);
}
