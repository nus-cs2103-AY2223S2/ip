package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class ListCommand extends Command {

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.listTasks();
        saver.save(taskList);
        return message;
    }
}
