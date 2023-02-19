package Duke.Commands;

import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader) {
        taskList.deleteTask(index);
        return null;
    }
}
