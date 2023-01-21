package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;


public class ByeCommand implements Command {
    @Override
    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionDispatcher.of(new ExecutionTask<String>(args, manager) {
            @Override
            public String process(ArgumentMap args, AppManager manager) {
                manager.setClose(true);
                return "Bye bye";
            }

            @Override
            public String getDataDisplay(String data, AppManager manager) {
                return data;
            }
        });
    }
}
