package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.exception.DuplicateTaskException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

/**
 * Command that executes the creation of an event task.
 *
 * @author Fun Leon
 */
public class CreateEventCommand extends Command {

    /** Title of the task */
    private String taskDescription;

    /** When the event is starts */
    private String startTime;

    /** When the event is ends */
    private String endTime;

    /**
     * Constructs a command that can create and add an event
     * task to the existing tasks.
     *
     * @param taskDescription Title of the event.
     * @param startTime When the event starts.
     * @param endTime When the event ends.
     */
    public CreateEventCommand(String taskDescription,
            String startTime, String endTime) {
        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates and adds new event task to existing tasks, and saves it locally.
     * The new task is unmarked.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public String execute(TaskManager taskManager, Storage storage, Ui ui)
            throws DataFileInteractionException, DuplicateTaskException {
        taskManager.addEvent(taskDescription, startTime, endTime);
        int taskNum = taskManager.getTaskCount();
        String taskString = taskManager.getTaskString(taskNum);
        String storageString = taskManager.getTaskStorageString(taskNum);
        storage.addLine(storageString);
        return ui.getAddTaskMsg(taskString, taskNum);
    }

}
