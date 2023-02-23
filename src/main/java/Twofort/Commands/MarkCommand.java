package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.markTask(index);
        saver.save(taskList);
        return message;

    }
}
