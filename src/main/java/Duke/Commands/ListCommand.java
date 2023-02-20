package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class ListCommand extends Command {
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        String message = taskList.listTasks();
        saver.save(taskList);
        return message;
    }
}
