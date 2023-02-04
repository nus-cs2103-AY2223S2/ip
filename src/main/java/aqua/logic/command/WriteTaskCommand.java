package aqua.logic.command;

import java.io.IOException;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.LogicManager;
import aqua.util.Kaomoji;


/** A {@code CommandController} to save {@code AquaTask} state. */
public class WriteTaskCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading) {
        return ExecutionService.of(new ExecutionTask<String>(args, manager) {
            @Override
            public String process(ArgumentMap args, LogicManager manager) {
                try {
                    manager.getTaskManager().saveToFile();
                } catch (IOException ioEx) {
                    return String.format(String.join("\n",
                                    "I could not put your tasks into the special place to remember things!",
                                    "Please help me with this:",
                                    "  %s",
                                    "If you leave me I might forget everything!!"),
                            ioEx.getMessage());
                }
                return "Safely stored hehe " + Kaomoji.SMUG;
            }


            @Override
            public String formDisplayMessage(String data, LogicManager manager) {
                return data;
            }
        });
    }
}
