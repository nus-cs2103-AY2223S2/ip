package jeno.command;

import jeno.exception.JenoException;
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
     * Extracts task that will be unmarked from user input.
     * @param userInput User input.
     * @param tasks Current Task List.
     * @return Task that will be unmarked.
     * @throws JenoException if index is invalid.
     */
    public Task getToUnmarkTask(String userInput, TaskList tasks) throws JenoException {
        int toUnmark;
        try {
            toUnmark = Integer.parseInt(userInput.substring(7));
            Task toUnmarkTask = tasks.getTask(toUnmark - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a valid index");
        }
        return tasks.getTask(toUnmark - 1);
    }

    /**
     * Executes user input and unmarks task specified by user.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that task has been unmarked.
     * @throws JenoException if index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws JenoException {
        Task toUnmarkTask = getToUnmarkTask(userInput, tasks);
        toUnmarkTask.unmarkTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've unmarked this task as incomplete:\n   "
                + toUnmarkTask + "\n";
    }
}
