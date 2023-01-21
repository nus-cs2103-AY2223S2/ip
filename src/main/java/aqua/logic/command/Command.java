package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.manager.AppManager;


public interface Command {
    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager);
}
