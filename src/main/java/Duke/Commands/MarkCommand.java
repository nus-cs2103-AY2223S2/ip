package Duke.Commands;

import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader) {
        taskList.markTask(index);
        return null;
    }
}
