package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        storage.save(task.getListOfTasks());
        ui.byeMessage();
    }
}
