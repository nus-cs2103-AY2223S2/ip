package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        String message = taskList.markTask(index);
        saver.save(taskList);
        return message;

    }
}
