package Duke.Commands;

import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader) {
        return taskList.unmarkTask(index);

    }
}
