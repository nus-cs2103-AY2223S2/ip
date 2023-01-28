import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    boolean isBye() {
        return true;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.sayBye();
        storage.saveTasks(tasks);
    }
}