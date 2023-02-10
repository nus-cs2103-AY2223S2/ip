package aqua.logic.command;

import java.util.LinkedHashMap;
import java.util.Map;

import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.usertask.UserTask;

/** A {@code CommandController} to filter {@code UserTask}. */
public class FilterCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new FilterTask(args, manager));
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new FilterDisplayerTask(args, logicManager, ioManager));
    }


    private static String formatMap(LinkedHashMap<Integer, UserTask> map) {
        if (map.isEmpty()) {
            return "Nothing!!";
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, UserTask> entry : map.entrySet()) {
            builder.append(String.format("%d. %s\n", entry.getKey(), entry.getValue()));
        }
        return builder.toString().strip();
    }





    private class FilterTask extends ExecutionTask<LinkedHashMap<Integer, UserTask>> {
        FilterTask(ArgumentMap args, LogicManager manager) {
            super(args, manager);
        }


        @Override
        public LinkedHashMap<Integer, UserTask> process(ArgumentMap args, LogicManager manager) {
            return manager.getTaskManager().filter(args.getMainInput().orElse(""));
        }
    }





    private class FilterDisplayerTask extends ExecutionDisplayerTask<LinkedHashMap<Integer, UserTask>> {
        FilterDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
            super(args, logicManager, ioManager);
        }


        @Override
        protected LinkedHashMap<Integer, UserTask> process(ArgumentMap args, LogicManager manager) {
            return manager.getTaskManager().filter(args.getMainInput().orElse(""));
        }


        @Override
        protected void display(LinkedHashMap<Integer, UserTask> map, IoManager manager) {
            manager.reply(String.format("Here are your matching tasks:\n%s",
                    formatMap(map)));
        }
    }
}
