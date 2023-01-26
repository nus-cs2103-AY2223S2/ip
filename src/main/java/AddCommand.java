import exception.TreeBotException;

import java.io.IOException;
import java.util.ArrayList;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TreeBotException {
        taskList.addTask(task);
        try {
            storage.saveTasks(taskList.getArrayListCopy());
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }

    }
}
