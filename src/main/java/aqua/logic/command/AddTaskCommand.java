package aqua.logic.command;

import aqua.aquatask.AquaTask;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;


/**
 * An abstract implementation of Command that will produce an
 * ExecutionDispatcher that will create and add an AquaTask to the task manager
 * and then save the state of the task manager to hard disk.
 */
public abstract class AddTaskCommand implements Command {
    /**
     * Creates an AquaTask from the given argument map.
     * 
     * @param args - the argument map.
     * @return an AquaTask created from the given arguments.
     * @throws IllegalSyntaxException if the arguments are of invalid syntax.
     */
    public abstract AquaTask createTask(ArgumentMap args) throws IllegalSyntaxException;


    @Override
    public ExecutionService getDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionService.of(new AddTask(args, manager))
                .setFollowUp(new WriteTaskCommand().getDispatcher(args, manager));
    }





    private class AddTask extends ExecutionTask<AquaTask> {
        AddTask(ArgumentMap args, AppManager manager) {
            super(args, manager);
        }


        @Override
        public AquaTask process(ArgumentMap args, AppManager manager) throws IllegalSyntaxException {
            // create task
            AquaTask task = createTask(args);

            // add task
            manager.getTaskManager().add(task);
            return task;
        }


        @Override
        public String getDataDisplay(AquaTask task, AppManager manager) {
            return String.format(String.join("\n",
                            "Hai okay desu! I have added the task:",
                            "/",
                            "  %s",
                            "\\",
                            "You now have %d tasks."),
                    task.toString(),
                    manager.getTaskManager().size());
        }
    }
}
