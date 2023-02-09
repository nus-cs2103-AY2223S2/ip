package Command;

import DukeException.DukeException;
import Task.TaskList;

/**
 * Command class for mark
 */
public class MarkCommand implements Command {
    private int markIndex;
    public MarkCommand(int i) {
        markIndex = i;
    }
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.mark(markIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
