import java.io.IOException;

public class ListCommand extends Command {


    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.writeFile(taskList);
        ui.outputListTask(taskList);
    }

}
