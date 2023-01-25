package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * The duke.commands.AddCommand class implements the action of adding tasks (to-do, deadline, and event).
 *
 * @author Chia Jeremy
 */

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(String description) {
        this.task = new Todo(description);
    }

    public AddCommand(String description, LocalDateTime dateTime) {
        this.task = new Deadline(description, dateTime);
    }

    public AddCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.task = new Event(description, startDateTime, endDateTime);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(this.task);
        storage.add(this.task);
        ui.display("Got it. I've added this task:\n"
                + this.task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
