package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        ui.informDeletion(tasks.getTask(taskIndex), tasks.getSize());
        tasks.deleteTask(taskIndex);
        Storage.saveTasksToTaskLog(tasks);
    }
}
