package aqua.logic.command;

import aqua.exception.ProcedureException;
import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.manager.TaskChangeReport;
import aqua.usertask.UserTask;
import aqua.util.Kaomoji;


/** A {@code CommandController} to add {@code UserTask}. */
public abstract class AddTaskCommand extends CommandController {
    /**
     * Creates an {@code AquaTask} from the given argument map.
     *
     * @param args - the argument map.
     * @return the task created from the given arguments.
     * @throws SyntaxException if the arguments are of invalid syntax.
     */
    protected abstract UserTask createTask(ArgumentMap args) throws SyntaxException, ProcedureException;


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new AddTask(args, manager));
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new AddDisplayerTask(args, logicManager, ioManager))
                .setFollowUp(new WriteTaskCommand().getService(args, logicManager, ioManager));
    }


    private TaskChangeReport addProcess(ArgumentMap args, LogicManager manager)
                throws SyntaxException, ProcedureException {
        UserTask task = createTask(args);
        return manager.getTaskManager().add(task);
    }





    private class AddTask extends ExecutionTask<TaskChangeReport> {
        AddTask(ArgumentMap args, LogicManager manager) {
            super(args, manager);
        }


        @Override
        protected TaskChangeReport process(ArgumentMap args, LogicManager manager)
                    throws SyntaxException, ProcedureException {
            return addProcess(args, manager);
        }
    }





    private class AddDisplayerTask extends ExecutionDisplayerTask<TaskChangeReport> {
        AddDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
            super(args, logicManager, ioManager);
        }


        @Override
        protected TaskChangeReport process(ArgumentMap args, LogicManager manager)
                    throws SyntaxException, ProcedureException {
            return addProcess(args, manager);
        }


        @Override
        protected void display(TaskChangeReport report, IoManager manager) {
            manager.reply(String.format(String.join("\n",
                            "Hai okay desu! I have added the task:",
                            Kaomoji.WAVE_UP,
                            "%s",
                            Kaomoji.WAVE_DOWN),
                    report.task.toString()));
            manager.reply(String.format(
                "You now have %d tasks.",
                report.numTask));
        }
    }
}
