import java.io.IOException;
import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    AddDeadlineCommand(String description, String deadline) {
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
