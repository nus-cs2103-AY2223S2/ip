package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.LogicManager;
import aqua.manager.TaskManager;


/** A {@code CommandController} to list {@code AquaTask}. */
public class ListCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading) {
        return ExecutionService.of(new ExecutionTask<TaskManager>(args, manager) {
            @Override
            public TaskManager process(ArgumentMap args, LogicManager manager) {
                return manager.getTaskManager();
            }


            @Override
            public String formDisplayMessage(TaskManager taskManager, LogicManager manager) {
                return String.format(String.join("\n",
                                "Here is your task list ( ꜆ 'ᵕ' )꜆",
                                "%s"),
                        getListMessage(taskManager));
            }
        });
    }


    private String getListMessage(TaskManager manager) {
        if (manager.size() > 0) {
            return manager.toString();
        }
        return "Nothing!! - ̗̀ ( ˶'ᵕ'˶) ̖́-";
    }


    @Override
    public String getDescription() {
        return "Displayes a view of your task list";
    }
}
