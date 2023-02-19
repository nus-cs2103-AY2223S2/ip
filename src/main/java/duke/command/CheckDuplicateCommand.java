package duke.command;

import java.util.HashMap;

import duke.task.Task;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.UI.UI;

public class CheckDuplicateCommand extends Command {
    public static final String COMMAND = "check_duplicate";

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            Task task = taskList.getTask(i);

            if (hashMap.get(task.toString()) != null) {
                ui.printResponse("Goodness me. I have detected repeated tasks. \n" +
                        "Tasks " + (hashMap.get(task.toString()) + 1) + " and " + (i + 1) +
                        " are duplicates. \nPlease make amendments.");
            }
            hashMap.put(task.toString(), i);
        }
        ui.printResponse("Good work! I did not find any duplicate tasks.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
