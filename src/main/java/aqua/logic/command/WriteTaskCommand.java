package aqua.logic.command;

import java.io.IOException;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.LogicManager;


/**
 * An implementation of Command that will produce an ExecutionService that
 * will save the state of the task manager to hard disk.
 */
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
                return "Safely stored hehe (  •̀֊•́ )";
            }


            @Override
            public String formDisplayMessage(String data, LogicManager manager) {
                return data;
            }
        });
    }
}
