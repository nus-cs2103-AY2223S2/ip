package duke.command;

import duke.taskstorage.Storage;
import duke.taskstorage.TaskList;
import duke.task.Task;

/**
 * Class for UnmarkCommand.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructor for UnmarkCommand.
     * @param userInput User input.
     */
    public UnmarkCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes user input and unmarks task specified by user.
     * @param tasks Current TaskList.
     * @return Message to inform user that task has been unmarked.
     */
    @Override
    public String execute(TaskList tasks) {
        int toUnMark = Integer.parseInt(userInput.substring(7));
        Task toUnMarkTask = tasks.getTask(toUnMark - 1);
        toUnMarkTask.unmarkTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've unmarked this task as incomplete:\n   "
                + toUnMarkTask + "\n";
    }
}
