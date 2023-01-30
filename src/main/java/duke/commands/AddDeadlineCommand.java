package duke.commands;

import java.io.IOException;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Deadline(description, deadline);
            tasks.add(newTask);
            ui.showToUser("You have added: " + newTask.toString(), "You have " + tasks.getSize() + " tasks in the list.");
            try {
                storage.appendToFile(newTask);
            } catch (IOException e) {
                ui.showError("Unable to write to file. Please run Duke again.");
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
