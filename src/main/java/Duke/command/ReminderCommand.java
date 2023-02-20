package Duke.command;

import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage.Storage;
import Duke.Ui;

public class ReminderCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        StringBuilder output = new StringBuilder();
        output.append("These tasks need to be done in three days!! \n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.isDueSoon()) {
                output.append(task + "\n");
            }
        }
        return output.toString();
    }
}
