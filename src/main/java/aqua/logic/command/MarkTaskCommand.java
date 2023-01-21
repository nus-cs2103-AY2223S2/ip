package aqua.logic.command;

import aqua.aquatask.AquaTask;
import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;

public class MarkTaskCommand implements CommandDispatcherCreator {
    private boolean isCompletedMarker;


    public MarkTaskCommand(boolean isCompletedMarker) {
        this.isCompletedMarker = isCompletedMarker;
    }


    @Override
    public ExecutionDispatcher createDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionDispatcher.of(new ExecutionTask<AquaTask>(args, manager) {
            @Override
            public AquaTask process(ArgumentMap args, AppManager manager)
                    throws IllegalSyntaxException, ProcedureExecutionException {
                try {
                    String indexString = args.getMainInput().filter(num -> !num.isBlank())
                            .orElseThrow(() -> new IllegalSyntaxException("Task number disappered!"));
                    int index = Integer.parseInt(indexString) - 1;
                    return manager.getTaskManager().mark(index, isCompletedMarker);
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
                if (isCompletedMarker) {
                    return String.format(
                        "I have marked this task:\n" +
                        "  %s\n" +
                        "__(⌒(_ ́-ω・)▄【┻┳══━一",
                        task.toString()
                    );
                }
                return String.format(
                    "Uah (๑•﹏•) Okay I have unmarked the task:\n" +
                    "  %s\n",
                    task.toString()
                );
            }
        });
    }
}
