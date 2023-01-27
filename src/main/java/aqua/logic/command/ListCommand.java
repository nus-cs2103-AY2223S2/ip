package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.LogicManager;
import aqua.manager.TaskManager;


/**
 * An implementation of Command that will produce an ExecutionDispatcher that
 * will display the state the task manager.
 */
public class ListCommand implements Command {
    @Override
    public ExecutionService getDispatcher(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new ExecutionTask<TaskManager>(args, manager) {
            @Override
            public TaskManager process(ArgumentMap args, LogicManager manager) {
                return manager.getTaskManager();
            }

            
            @Override
            public String getDataDisplay(TaskManager taskManager, LogicManager manager) {
                return String.format(String.join("\n",
                                "Here is your task list",
                                "%s"),
                        getListMessage(taskManager));
            }
        });
    }


    private String getListMessage(TaskManager manager) {
        if (manager.size() > 0) {
            return manager.toString();
        }
        return "Nothing!!";
    }
}
