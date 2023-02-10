package aqua.logic.command;

import aqua.exception.ProcedureException;
import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDisplayerTask;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import aqua.usertask.UserTask;
import aqua.util.Kaomoji;

/** A {@code CommandController} to mark {@code UserTask} as complete. */
public class MarkTaskCommand extends CommandController {
    /** Value to pass {@link UserTask#mark(boolean)} when marking a task. */
    private boolean isCompletedMarker;


    /**
     * Constructs a {@code MarkTaskCommand}.
     *
     * @param isCompleteMarker - {@code true} to mark task as complete and
     *      {@code false} as incomplete.
     */
    public MarkTaskCommand(boolean isCompletedMarker) {
        this.isCompletedMarker = isCompletedMarker;
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager) {
        return ExecutionService.of(new MarkTask(args, manager));
    }


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        return ExecutionService.of(new MarkDisplayerTask(args, logicManager, ioManager))
                .setFollowUp(new WriteTaskCommand().getService(args, logicManager, ioManager));
    }


    private UserTask markTask(ArgumentMap args, LogicManager manager)
                throws SyntaxException, ProcedureException {
        try {
            // get task index
            String indexString = args.getMainInput().filter(num -> !num.isBlank())
                    .orElseThrow(() -> new SyntaxException("Task number disappered!"));
            int index = Integer.parseInt(indexString) - 1;

            return manager.getTaskManager().mark(index, isCompletedMarker);
        } catch (NumberFormatException numEx) {
            throw new SyntaxException("Task number given was not an integer");
        } catch (IndexOutOfBoundsException oobEx) {
            throw new ProcedureException(
                    "The task number given is out of bounds of my task counting capabilities");
        }
    }





    private class MarkTask extends ExecutionTask<UserTask> {
        MarkTask(ArgumentMap args, LogicManager manager) {
            super(args, manager);
        }


        @Override
        protected UserTask process(ArgumentMap args, LogicManager manager)
                    throws SyntaxException, ProcedureException {
            return markTask(args, manager);
        }
    }





    private class MarkDisplayerTask extends ExecutionDisplayerTask<UserTask> {
        MarkDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
            super(args, logicManager, ioManager);
        }


        @Override
        protected UserTask process(ArgumentMap args, LogicManager manager)
                    throws SyntaxException, ProcedureException {
            return markTask(args, manager);
        }


        @Override
        protected void display(UserTask task, IoManager manager) {
            String message;
            if (isCompletedMarker) {
                message = String.format(String.join("\n",
                                "I have marked this task:",
                                "  %s"),
                        task.toString());
            } else {
                message = String.format(String.join("\n",
                                "Uah " + Kaomoji.OH_NO + " Okay I have unmarked the task:",
                                "  %s"),
                        task.toString());
            }
            manager.reply(message);
        }
    }
}
