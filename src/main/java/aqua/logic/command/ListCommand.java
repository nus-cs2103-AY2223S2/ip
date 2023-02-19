package aqua.logic.command;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.manager.TaskManager;
import aqua.util.Kaomoji;


/** A {@code CommandController} to list {@code UserTask}. */
public class ListCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new ExecutionTask<TaskManager>(args, manager) {
            @Override
            public TaskManager process(ArgumentMap args, LogicManager manager) {
                return manager.getTaskManager();
            }
        });
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new ExecutionDisplayerTask<TaskManager>(args, logicManager, ioManager) {
            @Override
            protected TaskManager process(ArgumentMap args, LogicManager manager) {
                return manager.getTaskManager();
            }

            @Override
            protected void display(TaskManager taskManager, IoManager manager) {
                manager.reply(String.format(String.join("\n",
                                "Here is your task list " + Kaomoji.PLACING_DOWN,
                                "%s"),
                        getListMessage(taskManager)));
            }
        });
    }


    private String getListMessage(TaskManager manager) {
        if (manager.size() > 0) {
            return manager.toString();
        }
        return "Nothing!! " + Kaomoji.BLUSH;
    }
}
