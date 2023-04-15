package duke.commands;
import duke.exceptions.DukeMarkOutOfBounds;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class Mark implements Command {
    private int index;

    public Mark(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeMarkOutOfBounds {
        try {
            Task toMark = tasks.get(index);
            boolean isDone = toMark.mark();
            if (isDone) { // if isDone after mark
                ui.showMark(toMark.toString());
            } else {
                ui.showUnmark(toMark.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMarkOutOfBounds(index);
        }
    }

    public boolean isExit() {
        return false;
    }
}