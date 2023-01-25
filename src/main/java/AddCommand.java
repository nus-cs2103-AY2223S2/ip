import java.time.LocalDateTime;

/**
 * The AddCommand class implements the action of adding tasks (to-do, deadline, and event).
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
