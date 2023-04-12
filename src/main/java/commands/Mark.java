package commands;
import exceptions.DukeMarkOutOfBounds;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

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