package command;

import task.TaskManager;

public class ListCommand extends Command {
    private final TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void executeCommand() {
        taskManager.displayList();
    }
}
