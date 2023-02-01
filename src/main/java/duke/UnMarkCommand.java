package duke;

/**
 * Command class for unmark
 */
public class UnMarkCommand implements Command {
    private int unMarkIndex;
    UnMarkCommand(int i) {
        unMarkIndex = i;
    }

    @Override
    public void execute(TaskList taskList) throws DukeException {
        taskList.unmark(unMarkIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
