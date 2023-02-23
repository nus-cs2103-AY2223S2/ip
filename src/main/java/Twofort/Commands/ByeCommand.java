package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList taskList,  Saver saver) {
        saver.save(taskList);
        return "So long pardner!";
    }
}
