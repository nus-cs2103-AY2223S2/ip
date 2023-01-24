package aqua.logic.command;

import aqua.aquatask.AquaTask;
import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.ExecutionTask;
import aqua.manager.AppManager;


public abstract class AddTaskCommand implements Command {
    public abstract AquaTask createTask(ArgumentMap args) throws IllegalSyntaxException;


    @Override
    public ExecutionDispatcher getDispatcher(ArgumentMap args, AppManager manager) {
        return ExecutionDispatcher.of(new AddTask(args, manager))
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
