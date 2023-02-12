package duke.command;

import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;
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
     * @param notes Current Note.
     * @return Message to inform user that task has been unmarked.
     */
    @Override
    public String execute(TaskList tasks, Note notes) {
        int toUnMark = Integer.parseInt(userInput.substring(7));
        Task toUnMarkTask = tasks.getTask(toUnMark - 1);
        toUnMarkTask.unmarkTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've unmarked this task as incomplete:\n   "
                + toUnMarkTask + "\n";
    }
}
