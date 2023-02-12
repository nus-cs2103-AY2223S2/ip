package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

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
