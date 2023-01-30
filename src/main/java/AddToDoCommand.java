import java.io.IOException;

public class AddToDoCommand extends Command {
    private final String description;

    AddToDoCommand(String description) {
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
