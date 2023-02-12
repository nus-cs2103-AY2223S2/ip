package duke.command;

import duke.taskstorage.Storage;
import duke.taskstorage.TaskList;
import duke.task.Task;

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
     * Executes user input and marks task specified by user.
     * @param tasks current TaskList
     * @return Message to inform user that task has been marked.
     */
    @Override
    public String execute(TaskList tasks) {
        int toMark = Integer.parseInt(userInput.substring(5));
        Task toMarkTask = tasks.getTask(toMark - 1);
        toMarkTask.markTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've marked this task as done:\n"
                + toMarkTask + "\n";
    }
}
