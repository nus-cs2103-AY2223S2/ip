package Command;

import DukeException.DukeException;
import Task.TaskList;

/**
 * Command class for unmark
 */
public class UnMarkCommand implements Command {
    private int unMarkIndex;
    public UnMarkCommand(int i) {
        unMarkIndex = i;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.unmark(unMarkIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
