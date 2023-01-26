import java.io.IOException;

public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTaskResponse(tasks);
    }
}
