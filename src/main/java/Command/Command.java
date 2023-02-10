package Command;

import DukeException.DukeException;
import Task.TaskList;

/**
 * Command Interface
 */
public interface Command {
    public String execute(TaskList taskList) throws DukeException;
    public boolean isExit();
}
