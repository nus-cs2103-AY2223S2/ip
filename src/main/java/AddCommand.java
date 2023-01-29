import java.io.IOException;

public class AddCommand extends Command {
    private Task t;

    public AddCommand(Task t) {
        super();
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(this.t);
        ui.showMessage("Ok, I've added this task: ");
        ui.showMessage(t.toString());
        String msg = String.format("You now have %d task(s) in your list!", tasks.size());
        ui.showMessage(msg);
        storage.saveTasks(tasks.getTasks());
    }
}
