import java.io.IOException;

public class ExitCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        ui.sayGoodbye();
        try {
            storage.save(tasks.getTasksToSave());
        } catch (IOException e) {
            throw new DukeException("unable to save tasks");
        }
        ui.showLine();
    }
}
