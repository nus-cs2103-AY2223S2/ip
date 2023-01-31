package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

public class CreateEventCommand extends Command {

    String taskDescription;
    String startTime;
    String endTime;

    public CreateEventCommand(String taskDescription,
            String startTime, String endTime) {
        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates and adds new event task to existing tasks.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public void execute(TaskManager taskManager, Storage storage, Ui ui) throws DataFileInteractionException {
        String taskString = taskManager.addEvent(taskDescription,
                startTime, endTime);
        storage.addLine("E| |" + taskDescription + "|" + startTime
                + "|" + endTime);
        ui.addTaskMsg(taskString, taskManager.getTaskCount());
    }


}

