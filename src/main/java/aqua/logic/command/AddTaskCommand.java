package aqua.logic.command;

import aqua.aquatask.AquaTask;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionService;
import aqua.logic.ExecutionTask;
import aqua.manager.LogicManager;


/**
 * An abstract implementation of Command that will produce an
 * ExecutionService that will create and add an AquaTask to the task manager
 * and then save the state of the task manager to hard disk.
 */
public abstract class AddTaskCommand extends CommandController {
    /**
     * Creates an AquaTask from the given argument map.
     *
     * @param args - the argument map.
     * @return an AquaTask created from the given arguments.
     * @throws IllegalSyntaxException if the arguments are of invalid syntax.
     */
    public abstract AquaTask createTask(ArgumentMap args) throws IllegalSyntaxException;


    @Override
    public ExecutionService getService(ArgumentMap args, LogicManager manager, boolean isLoading) {
        ExecutionService service = ExecutionService.of(new AddTask(args, manager));
        if (isLoading) {
            return service;
        }
        return service.setFollowUp(new WriteTaskCommand().getService(args, manager));
    }





    private class AddTask extends ExecutionTask<AquaTask> {
        AddTask(ArgumentMap args, LogicManager manager) {
            super(args, manager);
        }


        @Override
        public AquaTask process(ArgumentMap args, LogicManager manager) throws IllegalSyntaxException {
            // create task
            AquaTask task = createTask(args);

            // add task
            manager.getTaskManager().add(task);
            return task;
        }


        @Override
        public String formDisplayMessage(AquaTask task, LogicManager manager) {
            return String.format(String.join("\n",
                            "Hai okay desu! I have added the task:",
                            "/／",
                            "  %s",
                            "\\＼",
                            "You now have %d tasks."),
                    task.toString(),
                    manager.getTaskManager().size());
        }
    }
}
