package commands;

import static commands.CommandType.PRIORITISE;

import nook.Storage;
import nook.TaskList;
import nook.Ui;
import tasks.Priority;
import tasks.Task;

/**
 * Represents the command that updates the priority
 * of a specific task in the tasklist.
 */
public class ReprioritiseCommand extends Command {
    private static final String UNKNOWN_TASK_MESSAGE = "Oopsies.. Seems like that task does not exist :(";
    private int taskIndex;

    /**
     * Constructs a new ReprioritiseCommand with the specified CommandType and taskIndex.
     *
     * @param type      the type of this Command
     * @param taskIndex the index of the Task to be updated
     */
    public ReprioritiseCommand(CommandType type, int taskIndex) {
        super(type);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this ReprioritiseCommand with a specified TaskList, Ui, and Storage.
     * Updates the priority of a specific task by increasing or decreasing it
     * depending on the CommandType of the current ReprioritiseCommand
     *
     * @param list    the TaskList to retrieve the existing task from
     * @param ui      the Ui to help inform the user of the update
     * @param storage the Storage to save the updated TaskList to
     * @return The execution result string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            return UNKNOWN_TASK_MESSAGE;
        } else {
            assert taskIndex > 0 : "Task Index cannot be less than 1";
            Task currentTask = list.getTask(taskIndex);
            Priority currentPriority = currentTask.getPriority();
            String resultMessage = "";
            if (this.getType().equals(PRIORITISE)) {
                currentTask.setPriority(Priority.getHigherPriority(currentPriority));
                resultMessage = "Alright! Seems like this task needs more attention,"
                        + "I've prioritised this task for you:\n" + currentTask.toString();
            } else {
                currentTask.setPriority(Priority.getLowerPriority(currentPriority));
                resultMessage = "Alright! Seems like this task needs less attention,"
                        + "I've decreased the priority of this task for you:\n" + currentTask.toString();
            }
            storage.saveListToFile(list, ui);
            return resultMessage;
        }
    }
}
