package duke.commands;

import java.util.HashMap;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates check_duplicate command and its arguments.
 */
public class CheckDuplicateCommand extends Command {
    public static final String COMMAND = "check_duplicate";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (map.get(t.toString()) != null) {
                ui.addToResponseMessage("There exist duplicate tasks! Tasks "
                        + (map.get(t.toString()) + 1) + " and " + (i + 1) + " are duplicates.");
                return;
            }
            map.put(t.toString(), i);
        }
        ui.addToResponseMessage("There's no duplicate task :)");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
