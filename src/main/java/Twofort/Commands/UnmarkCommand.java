package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.unmarkTask(index);
        saver.save(taskList);
        return message;

    }
}
