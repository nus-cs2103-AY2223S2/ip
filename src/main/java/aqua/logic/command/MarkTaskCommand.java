package aqua.logic.command;

import aqua.aquatask.AquaTask;
import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;

public class MarkTaskCommand implements Command {
    private boolean isCompletedMarker;


    public MarkTaskCommand(boolean isCompletedMarker) {
        this.isCompletedMarker = isCompletedMarker;
    }


    @Override
    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionDispatcher.of(new MarkTask(args, manager))
                .setFollowUp(new WriteTaskCommand().getDispatcher(args, manager));
    }





    private class MarkTask extends ExecutionTask<AquaTask> {
        MarkTask(ArgumentMap args, AppManager manager) {
            super(args, manager);
        }


        @Override
        public AquaTask process(ArgumentMap args, AppManager manager)
                    throws IllegalSyntaxException, ProcedureExecutionException {
            try {
                // get index String
                String indexString = args.getMainInput().filter(num -> !num.isBlank())
                        .orElseThrow(() -> new IllegalSyntaxException("Task number disappered!"));
                
                // parse index String (minus 1 as user enters 1 based index)
                int index = Integer.parseInt(indexString) - 1;

                // mark and return marked task
                return manager.getTaskManager().mark(index, isCompletedMarker);
            } catch (NumberFormatException numEx) {
                throw new IllegalSyntaxException("Task number given was not an integer");
            } catch (IndexOutOfBoundsException oobEx) {
                throw new ProcedureExecutionException(
                        "The task number given is out of bounds of my task counting capabilities");
            }
        }

        @Override
        public String getDataDisplay(AquaTask task, AppManager manager) {
            if (isCompletedMarker) {
                return String.format(String.join("\n",
                                "I have marked this task:",
                                "  %s"),
                        task.toString());
            }
            return String.format(String.join("\n",
                            "Uah... Okay I have unmarked the task:",
                            "  %s"),
                    task.toString());
        }
    }
}
