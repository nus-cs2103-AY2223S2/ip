package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.LogicManager;


/**
 * An implementation of CommandController to say bye.
 */
public class ByeCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading) {
        return ExecutionService.of(new ExecutionTask<String>(args, manager) {
            @Override
            public String process(ArgumentMap args, LogicManager manager) {
                manager.setClose(true);
                return "Bye bye";
            }

            @Override
            public String formDisplayMessage(String msg, LogicManager manager) {
                return msg;
            }
        });
    }


    @Override
    public String getDescription() {
        return "Does absolutely nothing";
    }
}
