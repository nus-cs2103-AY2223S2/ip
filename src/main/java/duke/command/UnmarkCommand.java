package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        int toUnMark = userInput.charAt(7) - 48;
        Task toUnMarkTask = tasks.getTask(toUnMark - 1);
        toUnMarkTask.unmarkTask();
        ui.informTaskIsUnMarked(toUnMarkTask);
        Storage.saveTasksToTaskLog(tasks);
    }
}
