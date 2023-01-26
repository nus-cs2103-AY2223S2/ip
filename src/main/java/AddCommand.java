import exception.TreeBotException;

import java.io.IOException;
import java.util.ArrayList;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws TreeBotException {
        taskList.add(task);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }

    }
}
