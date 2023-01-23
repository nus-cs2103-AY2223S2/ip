package aqua.logic.command;

import java.io.IOException;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;


public class WriteTaskCommand implements Command {
    @Override
    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionDispatcher.of(new ExecutionTask<String>(args, manager) {
            @Override
            public String process(ArgumentMap args, AppManager manager) {
                try {
                    manager.getTaskManager().saveToFile();
                } catch (IOException ioEx) {
                    return String.format(
                        "I could not put your tasks into the special place to remember things!\n" +
                        "Please help me with this:\n" +
                        "  %s" +
                        "If you leave me I might forget everything!!",
                        ioEx.getMessage()
                    );
                }
                return "Safely stored hehe";
            }

            @Override
            public String getDataDisplay(String data, AppManager manager) {
                return data;
            }
        });
    }
}
