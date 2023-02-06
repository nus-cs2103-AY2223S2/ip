package aqua.logic.command;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.usertask.UserTask;
import aqua.util.Kaomoji;


/** A {@code CommandController} to add {@code AquaTask}. */
public abstract class AddTaskCommand extends CommandController {
    /**
     * Creates an {@code AquaTask} from the given argument map.
     *
     * @param args - the argument map.
     * @return the task created from the given arguments.
     * @throws SyntaxException if the arguments are of invalid syntax.
     */
    public abstract UserTask createTask(ArgumentMap args) throws SyntaxException;


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new AddTask(args, manager));
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new AddDisplayerTask(args, logicManager, ioManager))
                .setFollowUp(new WriteTaskCommand().getService(args, logicManager));
    }


    private UserTask addProcess(ArgumentMap args, LogicManager manager) throws SyntaxException {
        UserTask task = createTask(args);
        manager.getTaskManager().add(task);
        return task;
    }





    private class AddTask extends ExecutionTask<UserTask> {
        AddTask(ArgumentMap args, LogicManager manager) {
            super(args, manager);
        }


        @Override
        public UserTask process(ArgumentMap args, LogicManager manager) throws SyntaxException {
            return addProcess(args, manager);
        }
    }





    private class AddDisplayerTask extends ExecutionDisplayerTask<UserTask> {
        AddDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
            super(args, logicManager, ioManager);
        }


        @Override
        public UserTask process(ArgumentMap args, LogicManager manager) throws SyntaxException {
            return addProcess(args, manager);
        }


        @Override
        protected void display(UserTask task, IoManager manager) {
            manager.reply(String.format(String.join("\n",
                            "Hai okay desu! I have added the task:",
                            Kaomoji.WAVE_UP,
                            "  %s",
                            Kaomoji.WAVE_DOWN),
                    task.toString()));
        }
    }
}
