package duke;

import duke.Command;
import duke.DukeException;

public class MarkCommand implements Command {
    private int markIndex;
    MarkCommand(int i) {
        markIndex = i;
    }
    @Override
    public void execute(TaskList taskList) throws DukeException {
        taskList.mark(markIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
