import exception.TreeBotException;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws TreeBotException {
        taskList.remove(index - 1);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }
    }
}
