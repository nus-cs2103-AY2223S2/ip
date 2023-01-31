package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

import java.io.FileNotFoundException;

public class CreateDeadlineCommand extends Command {

    String taskDescription;
    String deadline;

    public CreateDeadlineCommand(String taskDescription, String deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Creates and adds new deadline task to existing tasks.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public void execute(TaskManager taskManager, Storage storage, Ui ui) throws DataFileInteractionException {
        String taskString = taskManager.addDeadline(taskDescription, deadline);
        storage.addLine("D| |" + taskDescription + "|" + deadline);
        ui.addTaskMsg(taskString, taskManager.getTaskCount());
    }


}
