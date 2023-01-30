import java.io.IOException;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        ui.showToUser("You have added: " + newTask.toString(), "You have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.appendToFile(newTask);
        } catch (IOException e) {
            ui.showError("Unable to write to file. Please run Duke again.");
        }
    }
}
