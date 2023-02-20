package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        return taskList.unmarkTask(index);

    }
}
