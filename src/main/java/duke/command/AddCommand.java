package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printToFormat("Task successfully added:\n    " + task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}