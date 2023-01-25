import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
        ui.showGoodbye();
        setExit();
    }
}
