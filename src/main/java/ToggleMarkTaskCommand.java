import exception.TreeBotException;

import java.io.IOException;
import java.util.ArrayList;

public class ToggleMarkTaskCommand extends Command {
    private int index;
    private boolean isMarkAsDone;
    public ToggleMarkTaskCommand(int index, boolean isMarkAsDone) {
        this.index = index;
        this.isMarkAsDone = isMarkAsDone;
    }
    @Override
    public void execute(ArrayList<Task> taskList, Ui u, Storage storage) throws TreeBotException {
        if (!isMarkAsDone) {
            taskList.get(index - 1).markAsUndone();
        } else {
            taskList.get(index - 1).markAsDone();
        }

        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }
    }
}
