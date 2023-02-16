package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;
import jeno.task.Task;

/**
 * Class for MarkCommand.
 */
public class MarkCommand extends Command {

    /**
     * Constructor for MarkCommand.
     * @param userInput User input.
     */
    public MarkCommand(String userInput) {
        super(userInput);
    }

    /**
     * Extracts task that will be marked from user input.
     * @param userInput User input.
     * @param tasks Current Task List.
     * @return Task that will be marked.
     * @throws JenoException if index is invalid.
     */
    public Task getToMarkTask(String userInput, TaskList tasks) throws JenoException {
        int toMark = Integer.parseInt(userInput.substring(5));
        try {
            Task toMarkTask = tasks.getTask(toMark - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new JenoException("Oops! Please enter a valid index");
        }
        return tasks.getTask(toMark - 1);
    }

    /**
     * Executes user input and marks task specified by user.
     * @param tasks current TaskList
     * @param notes Current Note.
     * @return Message to inform user that task has been marked.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws JenoException {
        Task toMarkTask = getToMarkTask(userInput, tasks);
        toMarkTask.markTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've marked this task as done:\n"
                + toMarkTask + "\n";
    }
}
