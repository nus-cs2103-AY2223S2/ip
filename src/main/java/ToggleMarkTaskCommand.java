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
    public void execute(TaskList taskList, Ui u, Storage storage) throws TreeBotException {
        if (!isMarkAsDone) {
            taskList.unmarkTask(index);
        } else {
            taskList.markTask(index);
        }

        try {
            storage.saveTasks(taskList.getArrayListCopy());
        } catch (IOException e) {
            throw new TreeBotException(e.getMessage());
        }
    }
}
