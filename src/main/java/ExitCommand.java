import java.io.IOException;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.writeFile(taskList);
        ui.outputExit();
    }

}
