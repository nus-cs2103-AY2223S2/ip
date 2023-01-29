import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex >= tasks.size()) {
            String errorMessage = String.format("Task %d does not exist!", taskIndex + 1);
            throw new DukeException(errorMessage);
        } else {
            Task task = tasks.get(this.taskIndex);
            ui.showMessage("Ok, I've deleted the following task for you:");
            ui.showMessage(task.toString());
            tasks.remove(this.taskIndex);
            String msg = String.format("You now have %d task(s) in your list!", tasks.size());
            ui.showMessage(msg);
            storage.saveTasks(tasks.getTasks());
        }
    }
}
