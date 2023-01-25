package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.markTask(index);
        ui.showMark(task.getTask(index));
    }
}
