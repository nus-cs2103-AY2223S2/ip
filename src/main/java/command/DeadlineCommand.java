package command;

import task.Deadline;
import task.TaskManager;

public class DeadlineCommand extends Command {
    private final TaskManager taskManager;
    private final String description;
    public DeadlineCommand(TaskManager taskManager, String description) {
        this.taskManager = taskManager;
        this.description = description;
    }
    @Override
    public void executeCommand() {
        String[] tmp = this.description.split(" /by ");
        Deadline deadline = new Deadline(tmp[0], tmp[1]);
        taskManager.addTaskToList(deadline);
    }
}
