package Duke.Commands;

import Duke.Saver;
import Duke.Tasks.TaskList;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.deleteTask(index);
        saver.save(taskList);
        return message;
    }
}
