package duke.command;

import duke.taskstorage.Storage;
import duke.taskstorage.TaskList;
import duke.task.Task;

/**
 * Class for DeleteCommand.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand class.
     * @param userInput User input.
     */
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes user input and deletes specified task from current TaskList.
     * @param tasks Current TaskList.
     * @return Message to inform user that task has been deleted.
     */
    @Override
    public String execute(TaskList tasks) {
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task toDelete = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        Storage.saveTasksToTaskLog(tasks);
        String taskCount = (tasks.getSize() - 1 == 1) ? "task" : "tasks";
        return ("Got it. I've removed this task:\n   "
                + toDelete
                + "\nNow you have " + (tasks.getSize()) + " " + taskCount + " in your list\n");
    }
}
