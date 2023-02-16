package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class RepeatCommand extends Command{
    private final int index;
    private final String recurrence;

    public RepeatCommand(String recurrence, int index) {
        this.index = index - 1;
        this.recurrence = recurrence;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (isInvalidIndex(taskList.getTotalNumOfTasks())) {
            throw new DukeException("Index is out of bound");
        }
        if (isInvalidRecurrence()) {
            throw new DukeException("Please enter the right repeating factor");
        }
        Task t = taskList.addRecurrence(index, recurrence);
        storage.writeFile(taskList);
        ui.outputAddRecurrence(t);
    }
    public boolean isInvalidIndex(int totalNumOfTasks) {
        return index < 0 || index >= totalNumOfTasks;
    }

    public boolean isInvalidRecurrence() {
        if (recurrence.equals("daily") || recurrence.equals("weekly") || recurrence.equals("monthly") || recurrence.equals("yearly")) {
            return false;
        } else {
            return true;
        }
    }
}
