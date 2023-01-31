package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        ui.showToUser("You have added: " + newTask.toString(), "You have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.appendToFile(newTask);
        } catch (IOException e) {
            throw new DukeException("Unable to write to file. Please run Duke again.");
        }
    }
}
