package aqua.logic.command;

import aqua.aquatask.AquaTask;
import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;

public class DeleteCommand implements Command {
    @Override
    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionDispatcher.of(new ExecutionTask<AquaTask>(args, manager) {
            @Override
            public AquaTask process(ArgumentMap args, AppManager manager)
                    throws IllegalSyntaxException, ProcedureExecutionException {
                try {
                    String indexString = args.getMainInput().filter(num -> !num.isBlank())
                            .orElseThrow(() -> new IllegalSyntaxException("Task number disappered!"));
                    int index = Integer.parseInt(indexString) - 1;
                    return manager.getTaskManager().delete(index);
                } catch (NumberFormatException numEx) {
                    throw new IllegalSyntaxException("Task number given was not an integer");
                } catch (IndexOutOfBoundsException oobEx) {
                    throw new ProcedureExecutionException(
                        "The task number given is out of bounds of my task counting capabilities"
                    );
                }
            }


            @Override
            public String getDataDisplay(AquaTask task, AppManager manager) {
                return String.format(
                    "I have deleted the task:\n" +
                    "  %s\n" +
                    "%s",
                    task.toString(),
                    getRemainingMessage(manager)
                );
            }
        }).setFollowUp(new WriteTaskCommand().getDispatcher(args, manager));
    }


    private String getRemainingMessage(AppManager manager) {
        int numTask = manager.getTaskManager().size();
        if (numTask > 0) {
            return String.format(
                "You have %d task(s) left, all the best",
                numTask
            );
        }
        return "You have no task left~";
    }
}
