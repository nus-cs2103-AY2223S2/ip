package Duke.Commands;

import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public class ListCommand extends Command {
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader) {
        return taskList.listTasks();
    }
}
