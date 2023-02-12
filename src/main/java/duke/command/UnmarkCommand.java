package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks) {
        int toUnMark = userInput.charAt(7) - 48;
        Task toUnMarkTask = tasks.getTask(toUnMark - 1);
        toUnMarkTask.unmarkTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've unmarked this task as incomplete:\n   "
                + toUnMarkTask + "\n";
    }
}
