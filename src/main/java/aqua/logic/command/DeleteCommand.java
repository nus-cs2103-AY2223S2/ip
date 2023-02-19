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
import aqua.util.Kaomoji;


/** A {@code CommandController} to delete {@code UserTask}. */
public class DeleteCommand extends CommandController {
    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new DeleteTask(args, manager));
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new DeleteDisplayerTask(args, logicManager, ioManager))
                .setFollowUp(new WriteTaskCommand().getService(args, logicManager, ioManager));
    }


    private TaskChangeReport deleteTask(ArgumentMap args, LogicManager manager)
                throws SyntaxException, ProcedureException {
        try {
            // get task index
            String indexString = args.getMainInput().filter(num -> !num.isBlank())
                    .orElseThrow(() -> new SyntaxException("Task number disappered!"));
            int index = Integer.parseInt(indexString) - 1;

            return manager.getTaskManager().delete(index);
        } catch (NumberFormatException numEx) {
            throw new SyntaxException("Task number given was not an integer");
        } catch (IndexOutOfBoundsException oobEx) {
            throw new ProcedureException(
                    "The task number given is out of bounds of my task counting capabilities");
        }
    }





    private class DeleteTask extends ExecutionTask<TaskChangeReport> {
        DeleteTask(ArgumentMap args, LogicManager manager) {
            super(args, manager);
        }


        @Override
        protected TaskChangeReport process(ArgumentMap args, LogicManager manager)
                    throws SyntaxException, ProcedureException {
            return deleteTask(args, manager);
        }
    }





    private class DeleteDisplayerTask extends ExecutionDisplayerTask<TaskChangeReport> {
        DeleteDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
            super(args, logicManager, ioManager);
        }


        @Override
        protected TaskChangeReport process(ArgumentMap args, LogicManager manager)
                    throws SyntaxException, ProcedureException {
            return deleteTask(args, manager);
        }


        @Override
        protected void display(TaskChangeReport report, IoManager manager) {
            manager.reply(String.format(String.join("\n",
                            "I have deleted the task:",
                            "%s"),
                    report.task.toString()));
            if (report.numTask > 0) {
                manager.reply(String.format(
                        "You have %d task(s) left, all the best " + Kaomoji.CHEER_ENCOURAGE,
                        report.numTask));
            } else {
                manager.reply(String.format(
                        Kaomoji.CHEER_EXCITED + " You have no task left~ " + Kaomoji.STAR_WHITE,
                        report.numTask));
            }
        }
    }
}
