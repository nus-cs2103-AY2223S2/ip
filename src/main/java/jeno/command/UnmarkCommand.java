package jeno.command;

import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;
import jeno.task.Task;

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
