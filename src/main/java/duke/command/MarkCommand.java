package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int markNumber;
    private final boolean isMark;

    public MarkCommand(int markNumber, boolean isMark) {
        this.markNumber = markNumber;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        if (tl.isEmpty()) {
            throw new DukeException("How to mark or unmark an empty list of tasks meowww");
        }
        if (this.markNumber > tl.size() || this.markNumber < 1) {
            throw new DukeException("Out of range you can mark or unmark!");
        }
        String toShow;
        if (isMark) {
            toShow = tl.markTask(markNumber - 1);
            ui.showToUser(toShow);
        } else {
            toShow = tl.unmarkTask(markNumber - 1);
            ui.showToUser(toShow);
        }

        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in delete");
        }

    }
}
