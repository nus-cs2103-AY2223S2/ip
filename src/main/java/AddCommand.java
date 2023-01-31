import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        tasklist.addTask(this.task);
        storage.update(tasklist);
        ui.printAddTaskMessage(this.task, tasklist);
    }
}