package aqua.logic.command;

import java.util.LinkedHashMap;
import java.util.Map;

import aqua.aquatask.AquaTask;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;

/** A {@code CommandController} to filter {@code AquaTask}. */
public class FilterCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new ExecutionTask<LinkedHashMap<Integer, AquaTask>>(args, manager) {
            @Override
            public LinkedHashMap<Integer, AquaTask> process(ArgumentMap args, LogicManager manager) {
                return manager.getTaskManager().filter(args.getMainInput().orElse(""));
            }
        });
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(
                new ExecutionDisplayerTask<LinkedHashMap<Integer, AquaTask>>(args, logicManager, ioManager) {
                    @Override
                    public LinkedHashMap<Integer, AquaTask> process(ArgumentMap args, LogicManager manager) {
                        return manager.getTaskManager().filter(args.getMainInput().orElse(""));
                    }

                    @Override
                    public void display(LinkedHashMap<Integer, AquaTask> map, IoManager manager) {
                        manager.reply(String.format("Here are your matching tasks:\n%s",
                                formatMap(map)));
                    }
                });
    }


    private static String formatMap(LinkedHashMap<Integer, AquaTask> map) {
        if (map.isEmpty()) {
            return "Nothing!!";
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, AquaTask> entry : map.entrySet()) {
            builder.append(String.format("%d. %s\n", entry.getKey(), entry.getValue()));
        }
        return builder.toString().strip();
    }


    @Override
    public String getSyntax() {
        return "<literal:pattern>";
    }


    @Override
    public String getDescription() {
        return "Displayes a filtered view of your task list";
    }
}
