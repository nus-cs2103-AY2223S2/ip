package commands;

import static commands.CommandType.MARK;

import java.io.IOException;

import nook.Storage;
import nook.TaskList;
import nook.Ui;
import tasks.Task;

/**
 * Represents the command that toggles the completion status
 * of a specific task in the tasklist.
 */
public class MarkCommand extends Command {
    private static final String UNKNOWN_TASK_MESSAGE = "Oopsies.. Seems like that task does not exist :(";
    private int taskIndex;

    /**
     * Constructs a new MarkCommand with the specified CommandType and taskIndex.
     *
     * @param type the type of this Command
     * @param taskIndex the index of the Task to be updated
     */
    public MarkCommand(CommandType type, int taskIndex) {
        super(type);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this MarkCommand with a specified TaskList, Ui, and Storage.
     * Toggles the completion status of a specific task as done or not done
     * depending on the CommandType of the current MarkCommand
     *
     * @param list the TaskList to retrieve the existing task from
     * @param ui the Ui to help inform the user of the update
     * @param storage the Storage to save the updated TaskList to
     *
     * @return The execution result string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            return UNKNOWN_TASK_MESSAGE;
        }

        assert taskIndex > 0 : "Task Index cannot be less than 1";

        Task currentTask = list.getTask(taskIndex);
        String resultMessage;

        if (this.getType().equals(MARK)) {
            currentTask.markAsDone();
            resultMessage = "Great job! I knew you could do it. Keep up the good work!\n"
                    + "I've marked this task as done:\n"
                    + currentTask;
        } else {
            currentTask.markAsNotDone();
            resultMessage = "Alright, I've marked this task as not done:\n"
                    + currentTask;
        }

        try {
            storage.saveListToFile(list, ui);
        } catch (IOException e) {
            return ui.getSavingError();
        }

        return resultMessage;
    }
}
