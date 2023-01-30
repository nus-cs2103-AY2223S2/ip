package duke.commands;

import java.io.IOException;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        ui.showToUser("You have added: " + newTask.toString(), "You have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.appendToFile(newTask);
        } catch (IOException e) {
            ui.showError("Unable to write to file. Please run Duke again.");
        }
    }
}
