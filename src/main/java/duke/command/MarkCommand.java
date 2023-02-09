package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class MarkCommand extends Command {
    public MarkCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks) {
        int toMark = userInput.charAt(5) - 48;
        Task toMarkTask = tasks.getTask(toMark - 1);
        toMarkTask.markTask();
        Storage.saveTasksToTaskLog(tasks);
        return "Nice! I've marked this task as done:\n"
                + toMarkTask + "\n";
    }
}
